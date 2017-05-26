package com.cn.core.token;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Random;

public class TokenList implements Serializable {
	private int delayTime;
	private int maxEntryNumber;
	private int tokenLength;
	private boolean Tokdo;
	private LinkedList linkedList;
	private static Random a = new SecureRandom();

	public TokenList(int maxEntryNumber, int delayTime, int tokenLength) {
		this(maxEntryNumber, delayTime, tokenLength, false);
	}

	// new TokenList(maxEntryNumber, delayTime, tokenLength);
	public TokenList(int maxEntryNumber, int delayTime, int tokenLength,
			boolean var4) {
		this.tokenLength = 6;
		this.linkedList = new LinkedList();
		this.maxEntryNumber = maxEntryNumber;
		this.delayTime = delayTime;
		this.tokenLength = tokenLength;
		this.Tokdo = var4;
	}

	public String toString() {
		return this.getClass().getName() + " :" + this.linkedList;
	}

	public String getNextTokenId() {
		long maxEntryNumber = a.nextLong();
		long tokenLength = maxEntryNumber >= 0L ? maxEntryNumber
				: -maxEntryNumber;
		StringBuffer sb = new StringBuffer(
				this.Tokdo ? Long.toString(tokenLength) : Long.toString(
						tokenLength, 36));

		while (sb.length() < this.tokenLength) {
			sb.insert(0, '0');
		}

		return sb.length() > this.tokenLength ? sb.substring(sb.length()
				- this.tokenLength) : sb.toString();
	}

	public void add(Token token) {
		synchronized (this.linkedList) {
			this.linkedList.addLast(token);
			if (this.linkedList.size() > this.maxEntryNumber) {
				this.linkedList.removeFirst();
			}

		}
	}

	public Token get(String s) {
		LinkedList linkedlist = this.linkedList;
		synchronized (this.linkedList) {
			for (int tokenLength = 0; tokenLength < this.linkedList.size(); ++tokenLength) {
				Token token = (Token) this.linkedList.get(tokenLength);
				if (token.getUniqueId().equals(s)) {
					long accDate = System.currentTimeMillis()
							- token.getAccessDate();
					if (accDate > (long) (this.delayTime * 1000)) {
						this.linkedList.remove(tokenLength);
						throw new RuntimeException("token_timeout");
					}

					this.linkedList.remove(tokenLength);
					return token;
				}
			}

			return null;
		}
	}
}
