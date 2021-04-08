---
title: 基于ssm的在线考试系统的设计与实现
date: 2021-07-02 19:00:00
tags: 
  - java
  - spring
  - springmvc
  - mybatis
  - git
category:
  - 小赫赫的项目日志
---

## 所需技术

本次期末项目采用以下技术，其中又分为前端部分和后端部分

### 后端部分

1. Spring + SpringMVC + MyBatis
2. Apache POI（处理word，excel）
3. pageHelper（处理分页）
4. JUnit（用于单元测试）

### 前端部分

1. layUI
2. jQuery（暂定）

## 项目协作

项目协作的部分使用 git，使用 gitee 作为代码仓库。在使用 git 作为多人协作会可能出现以下的问题，一个是不会用，另一个是会用之后，不知该如何去合并。

### 如何操作

#### 如果你是新加入的组员

1、首先获取项目：

```
git clone https://gitee.com/bestguo2020/ssm_online_exam.git
```

2、获取项目完成创建你的工作区（注意，xxx 为你的工作区名），创建完成之后就可以对你的工作区进行操作了

```
git checkout -b XXXX
```

3、将 master 工作区的代码合并到你的工作区中

```
git merge master
```

合并完成之后就可以开始写代码了

4、假如你不想写了，需要对你写过的代码进行提交，在提交代码时需要交代清楚你需要做了哪些工作，也就是xxxx里的内容需要填写的部分。

```
git add .
git commit -m "xxxxxx"
```

5、切换到 master 工作区

```
git checkout master
```

6、将你的工作区合并到 master 工作区（XXX 代表你的工作区）

```
git merge XXXX
```

7、推送到远程仓库之前，将 gitee 上的远程代码拉取下来

```
git pull origin master
```

8、开始推送，同时也需要将你的工作区推到 git（XXX 代表你的工作区）

```
git push origin master
git push origin XXX
```

#### 如果你是已经加入的组员

1、



### 问题

1、如何将其它分支合并到master分支