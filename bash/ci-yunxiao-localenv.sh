#!/bin/bash

# 设置运行目录
mkdir -p /root/opt
cd /root/opt

# 判断 graalvm-jdk-20_linux-x64_bin.tar.gz 是否存在，如果不存在则下载
if [ ! -f "graalvm-jdk-20_linux-x64_bin.tar.gz" ]; then
    echo "Downloading graalvm..."
    wget -O graalvm-jdk-20_linux-x64_bin.tar.gz https://download.oracle.com/graalvm/20/latest/graalvm-jdk-20_linux-x64_bin.tar.gz
fi

# 解压 graalvm
if [ ! -d "/root/localenv/graalvm" ]; then
    mkdir -p /root/localenv/graalvm
    tar -xf graalvm-jdk-20_linux-x64_bin.tar.gz -C /root/localenv/graalvm --strip-components=1
fi

# 设置 graalvm_home 环境变量
export GRAALVM_HOME=/root/localenv/graalvm
export PATH=$PATH:$GRAALVM_HOME/bin
# 判断 apache-maven-3.8.6-bin.tar.gz 是否存在，如果不存在则下载
if [ ! -f "apache-maven-3.8.6-bin.tar.gz" ]; then
    echo "Downloading apache-maven..."
    wget -O apache-maven-3.8.6-bin.tar.gz https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz
fi

# 解压 apache-maven
if [ ! -d "/root/localenv/apache-maven-3.8.6" ]; then
    mkdir -p /root/localenv/apache-maven-3.8.6
    tar -xf apache-maven-3.8.6-bin.tar.gz -C /root/localenv/apache-maven-3.8.6 --strip-components=1
fi

# 输出提示信息
echo "GraalVM and Apache Maven are ready!"


cd /root/workspace/fast-quarkus_DbBc
export JAVA_HOME=/root/workspace/fast-quarkus_DbBc/graalvm-jdk-20.0.2+9.1/
export PATH=$PATH:$JAVA_HOME/bin
/root/localenv/apache-maven-3.8.6/mvn  -v
/root/localenv/apache-maven-3.8.6/mvn -s /root/.m2/settings.xml clean package -Pnative -DJAVA_HOME=/root/workspace/fast-quarkus_DbBc/graalvm-jdk-20.0.2+9.1/bin

