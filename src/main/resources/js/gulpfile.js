/*
//���빤�߰� require('node_modules���Ӧģ��')
var gulp = require('gulp'), //���ذ�װgulp���õ��ĵط�
    less = require('gulp-less');
 
//����һ��testLess�����Զ����������ƣ�
gulp.task('testLess', function () {
    gulp.src('src/less/index.less') //��������Ե��ļ�
        .pipe(less()) //��������õ�ģ��
        .pipe(gulp.dest('src/css')); //������src/css������index.css
});
 
gulp.task('default',['testLess', 'elseTask']); //����Ĭ������ elseTaskΪ�������񣬸�ʾ��û�ж���elseTask����
 
//gulp.task(name[, deps], fn) ��������  name���������� deps�������������� fn���ص�����
//gulp.src(globs[, options]) ִ����������ļ�  globs��������ļ�·��(�ַ��������ַ�������) 
//gulp.dest(path[, options]) ��������ļ�����·��
*/
var gulp = require('gulp'),
    uglify= require('gulp-uglify');
	concat = require('gulp-concat');
	imagemin = require('gulp-imagemin'),//ѹ��ͼƬ
    pngquant = require('imagemin-pngquant'), // ���ѹ�� 
	
	
 
gulp.task('jsmin', function () {
	
	//	 1.����ļ���������ʽ����
	//   2.src/js/*.js  ---> ѹ��src/jsĿ¼�µ�����js�ļ�
    //   3. !testproject/src/js/**/{test1,test2}.js ---> ����test1.js��test2.js��**ƥ��src/js��0���������ļ��У�
	//
    gulp.src(['C:/myprojects/workspace/20150907/spdb-pits-server_20170324/src/main/webapp/js/scriptbak/**/*controller.js',
	'!testproject/src/js/**/{test1,test2}.js'])
        .pipe(uglify({
            /*
				mangle: true,//���ͣ�Boolean Ĭ�ϣ�true �Ƿ��޸ı�����
				compress: true,//���ͣ�Boolean Ĭ�ϣ�true �Ƿ���ȫѹ��
				preserveComments: 'all' //��������ע��
			
			*/
            mangle: {except: ['require' ,'exports' ,'module' ,'$ionicScrollDelegate','$scope','define','app', '$http', 'service', '$rootScope', '$state','$stateParams','$ionicSlideBoxDelegate','$ionicHistory','$location','$ionicSideMenuDelegate']}//�ų������ؼ���$scope, , $state, service, , $ionicHistory, 
        }))
        .pipe(gulp.dest('C:/myprojects/workspace/20150907/spdb-pits-server_20170324/src/main/webapp/js/script/'));
		
		//console.log(e.path);
});
//ͼƬѹ��
gulp.task('testImagemin', function () {
    gulp.src('C:/myprojects/workspace/20150907/spdb-pits-server_20170324/src/main/webapp/js/scriptbak/img/*.{png,jpg,gif,ico}')
        .pipe(imagemin({
            progressive: true,
            svgoPlugins: [{removeViewBox: false}],//��Ҫ�Ƴ�svg��viewbox����
            use: [pngquant()] //ʹ��pngquant���ѹ��pngͼƬ��imagemin���
        }))
        .pipe(gulp.dest('dist/img'));
});

//�ļ��ϲ�
gulp.task('testConcat', function () {
    gulp.src('C:/myprojects/workspace/20150907/spdb-pits-server_20170324/src/main/webapp/css/*.css')
        .pipe(concat('styles.css'))//�ϲ�����ļ���
        .pipe(gulp.dest('C:\\myprojects\\workspace\\20150907\\spdb-pits-server_20170324\\src\\main\\webapp\\css'));
});

gulp.task('default',['jsmin','testImagemin','testConcat']);
