#!/bin/bash
cd bash
chmod +x ci-yunxiao-localenv.sh
bash ci-yunxiao-localenv.sh

cd /root/workspace/fast-quarkus_DbBc
export JAVA_HOME=/root/opt/graalvm
export PATH=$PATH:$JAVA_HOME/bin

/root/opt/apache-maven-3.8.6/bin/mvn  -v
/root/opt/apache-maven-3.8.6/bin/mvn -s /root/.m2/settings.xml -DskipTests=true clean package  -Pnative
