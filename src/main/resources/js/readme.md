#### gulp gulp��ǰ�˿��������жԴ�����й����Ĺ��ߣ����Զ�����Ŀ�Ĺ����������������ܶ���վ��Դ�����Ż��������ڿ��������кܶ��ظ��������ܹ�ʹ����ȷ�Ĺ����Զ���ɣ�ʹ���������ǲ������Ժ����ı�д���룬���Ҵ��������ǵĹ���Ч�ʡ�

gulp�ǻ���Nodejs���Զ������������� �����Զ�������� javascript/coffee/sass/less/html/image/css ���ļ��ĵĲ��ԡ���顢�ϲ���ѹ������ʽ����������Զ�ˢ�¡������ļ����ɣ��������ļ��ڸĶ����ظ�ָ������Щ���衣��ʵ���ϣ��������Unix����ϵͳ�Ĺܵ���pipe��˼�룬ǰһ���������ֱ�ӱ�ɺ�һ�������룬ʹ���ڲ����Ϸǳ��򵥡�ͨ�����ģ����ǽ�ѧϰ���ʹ��Gulp���ı俪�����̣��Ӷ�ʹ�������ӿ��ٸ�Ч��

gulp �� grunt �ǳ����ƣ�������� grunt ��Ƶ�� IO ������gulp �����������ܸ���ظ���ݵ���ɹ���������



1. ȫ�ְ�װ gulp��

$ npm install --global gulp
2. ��Ϊ��Ŀ�Ŀ���������devDependencies����װ��

$ npm install --save-dev gulp
3. ����Ŀ��Ŀ¼�´���һ����Ϊ gulpfile.js ���ļ���

var gulp = require('gulp');

gulp.task('default', function() {
  // �����Ĭ�ϵ�������������
});
4. ���� gulp��

$ gulp
Ĭ�ϵ���Ϊ default ������task�����ᱻ���У�������������δ���κ����顣

��Ҫ����ִ���ض�������task���������� gulp <task> <othertask>��

#### gulp-uglify
ʹ��gulp-uglifyѹ��javascript�ļ�����С�ļ���С��

3.1������ʹ��
JavaScript
1
2
3
4
5
6
7
8
var gulp = require('gulp'),
    uglify = require('gulp-uglify');
 
gulp.task('jsmin', function () {
    gulp.src('src/js/index.js')
        .pipe(uglify())
        .pipe(gulp.dest('dist/js'));
});
3.2��ѹ�����js�ļ�
JavaScript
1
2
3
4
5
6
7
8
var gulp = require('gulp'),
    uglify = require('gulp-uglify');
 
gulp.task('jsmin', function () {
    gulp.src(['src/js/index.js','src/js/detail.js']) //����ļ���������ʽ����
        .pipe(uglify())
        .pipe(gulp.dest('dist/js'));
});
3.3��ƥ�����!������*������**������{}��
var gulp = require('gulp'),
    uglify= require('gulp-uglify');
 
gulp.task('jsmin', function () {
    //ѹ��src/jsĿ¼�µ�����js�ļ�
    //����test1.js��test2.js��**ƥ��src/js��0���������ļ��У�
    gulp.src(['src/js/*.js', '!src/js/**/{test1,test2}.js']) 
        .pipe(uglify())
        .pipe(gulp.dest('dist/js'));
});
3.4��ָ���������������ı�

var gulp = require('gulp'),
    uglify= require('gulp-uglify');
 
gulp.task('jsmin', function () {
    gulp.src(['src/js/*.js', '!src/js/**/{test1,test2}.js'])
        .pipe(uglify({
            //mangle: true,//���ͣ�Boolean Ĭ�ϣ�true �Ƿ��޸ı�����
            mangle: {except: ['require' ,'exports' ,'module' ,'$']}//�ų������ؼ���
        }))
        .pipe(gulp.dest('dist/js'));
});
3.5��gulp-uglify�������� ����ο�

var gulp = require('gulp'),
    uglify= require('gulp-uglify');
 
gulp.task('jsmin', function () {
    gulp.src(['src/js/*.js', '!src/js/**/{test1,test2}.js'])
        .pipe(uglify({
            mangle: true,//���ͣ�Boolean Ĭ�ϣ�true �Ƿ��޸ı�����
            compress: true,//���ͣ�Boolean Ĭ�ϣ�true �Ƿ���ȫѹ��
            preserveComments: 'all' //��������ע��
        }))
        .pipe(gulp.dest('dist/js'));
});

#### ʹ��gulp-concat�ϲ�javascript�ļ���������������


