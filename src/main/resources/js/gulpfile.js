/*
//导入工具包 require('node_modules里对应模块')
var gulp = require('gulp'), //本地安装gulp所用到的地方
    less = require('gulp-less');
 
//定义一个testLess任务（自定义任务名称）
gulp.task('testLess', function () {
    gulp.src('src/less/index.less') //该任务针对的文件
        .pipe(less()) //该任务调用的模块
        .pipe(gulp.dest('src/css')); //将会在src/css下生成index.css
});
 
gulp.task('default',['testLess', 'elseTask']); //定义默认任务 elseTask为其他任务，该示例没有定义elseTask任务
 
//gulp.task(name[, deps], fn) 定义任务  name：任务名称 deps：依赖任务名称 fn：回调函数
//gulp.src(globs[, options]) 执行任务处理的文件  globs：处理的文件路径(字符串或者字符串数组) 
//gulp.dest(path[, options]) 处理完后文件生成路径
*/
var gulp = require('gulp'),
    uglify= require('gulp-uglify');
	concat = require('gulp-concat');
	imagemin = require('gulp-imagemin'),//压缩图片
    pngquant = require('imagemin-pngquant'), // 深度压缩 
	
	
 
gulp.task('jsmin', function () {
	
	//	 1.多个文件以数组形式传入
	//   2.src/js/*.js  ---> 压缩src/js目录下的所有js文件
    //   3. !testproject/src/js/**/{test1,test2}.js ---> 除了test1.js和test2.js（**匹配src/js的0个或多个子文件夹）
	//
    gulp.src(['C:/myprojects/workspace/20150907/spdb-pits-server_20170324/src/main/webapp/js/scriptbak/**/*controller.js',
	'!testproject/src/js/**/{test1,test2}.js'])
        .pipe(uglify({
            /*
				mangle: true,//类型：Boolean 默认：true 是否修改变量名
				compress: true,//类型：Boolean 默认：true 是否完全压缩
				preserveComments: 'all' //保留所有注释
			
			*/
            mangle: {except: ['require' ,'exports' ,'module' ,'$ionicScrollDelegate','$scope','define','app', '$http', 'service', '$rootScope', '$state','$stateParams','$ionicSlideBoxDelegate','$ionicHistory','$location','$ionicSideMenuDelegate']}//排除混淆关键字$scope, , $state, service, , $ionicHistory, 
        }))
        .pipe(gulp.dest('C:/myprojects/workspace/20150907/spdb-pits-server_20170324/src/main/webapp/js/script/'));
		
		//console.log(e.path);
});
//图片压缩
gulp.task('testImagemin', function () {
    gulp.src('C:/myprojects/workspace/20150907/spdb-pits-server_20170324/src/main/webapp/js/scriptbak/img/*.{png,jpg,gif,ico}')
        .pipe(imagemin({
            progressive: true,
            svgoPlugins: [{removeViewBox: false}],//不要移除svg的viewbox属性
            use: [pngquant()] //使用pngquant深度压缩png图片的imagemin插件
        }))
        .pipe(gulp.dest('dist/img'));
});

//文件合并
gulp.task('testConcat', function () {
    gulp.src('C:/myprojects/workspace/20150907/spdb-pits-server_20170324/src/main/webapp/css/*.css')
        .pipe(concat('styles.css'))//合并后的文件名
        .pipe(gulp.dest('C:\\myprojects\\workspace\\20150907\\spdb-pits-server_20170324\\src\\main\\webapp\\css'));
});

gulp.task('default',['jsmin','testImagemin','testConcat']);
