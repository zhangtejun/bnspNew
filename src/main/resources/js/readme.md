#### gulp gulp是前端开发过程中对代码进行构建的工具，是自动化项目的构建利器；她不仅能对网站资源进行优化，而且在开发过程中很多重复的任务能够使用正确的工具自动完成；使用她，我们不仅可以很愉快的编写代码，而且大大提高我们的工作效率。

gulp是基于Nodejs的自动任务运行器， 她能自动化地完成 javascript/coffee/sass/less/html/image/css 等文件的的测试、检查、合并、压缩、格式化、浏览器自动刷新、部署文件生成，并监听文件在改动后重复指定的这些步骤。在实现上，她借鉴了Unix操作系统的管道（pipe）思想，前一级的输出，直接变成后一级的输入，使得在操作上非常简单。通过本文，我们将学习如何使用Gulp来改变开发流程，从而使开发更加快速高效。

gulp 和 grunt 非常类似，但相比于 grunt 的频繁 IO 操作，gulp 的流操作，能更快地更便捷地完成构建工作。



1. 全局安装 gulp：

$ npm install --global gulp
2. 作为项目的开发依赖（devDependencies）安装：

$ npm install --save-dev gulp
3. 在项目根目录下创建一个名为 gulpfile.js 的文件：

var gulp = require('gulp');

gulp.task('default', function() {
  // 将你的默认的任务代码放在这
});
4. 运行 gulp：

$ gulp
默认的名为 default 的任务（task）将会被运行，在这里，这个任务并未做任何事情。

想要单独执行特定的任务（task），请输入 gulp <task> <othertask>。

#### gulp-uglify
使用gulp-uglify压缩javascript文件，减小文件大小。

3.1、基本使用
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
3.2、压缩多个js文件
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
    gulp.src(['src/js/index.js','src/js/detail.js']) //多个文件以数组形式传入
        .pipe(uglify())
        .pipe(gulp.dest('dist/js'));
});
3.3、匹配符“!”，“*”，“**”，“{}”
var gulp = require('gulp'),
    uglify= require('gulp-uglify');
 
gulp.task('jsmin', function () {
    //压缩src/js目录下的所有js文件
    //除了test1.js和test2.js（**匹配src/js的0个或多个子文件夹）
    gulp.src(['src/js/*.js', '!src/js/**/{test1,test2}.js']) 
        .pipe(uglify())
        .pipe(gulp.dest('dist/js'));
});
3.4、指定变量名不混淆改变

var gulp = require('gulp'),
    uglify= require('gulp-uglify');
 
gulp.task('jsmin', function () {
    gulp.src(['src/js/*.js', '!src/js/**/{test1,test2}.js'])
        .pipe(uglify({
            //mangle: true,//类型：Boolean 默认：true 是否修改变量名
            mangle: {except: ['require' ,'exports' ,'module' ,'$']}//排除混淆关键字
        }))
        .pipe(gulp.dest('dist/js'));
});
3.5、gulp-uglify其他参数 具体参看

var gulp = require('gulp'),
    uglify= require('gulp-uglify');
 
gulp.task('jsmin', function () {
    gulp.src(['src/js/*.js', '!src/js/**/{test1,test2}.js'])
        .pipe(uglify({
            mangle: true,//类型：Boolean 默认：true 是否修改变量名
            compress: true,//类型：Boolean 默认：true 是否完全压缩
            preserveComments: 'all' //保留所有注释
        }))
        .pipe(gulp.dest('dist/js'));
});

#### 使用gulp-concat合并javascript文件，减少网络请求


