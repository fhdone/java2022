brew services start mysql
/Users/fhdone/dev/redis-6.0.9/src/redis-server /Users/fhdone/dev/redis-6.0.9/redis.conf
/Users/fhdone/dev/apache-zookeeper-3.9.1-bin/bin/zkServer.sh start

brew services stop mysql
/Users/fhdone/dev/apache-zookeeper-3.9.1-bin/bin/zkServer.sh stop


docker run -e REDIS_ARGS="--requirepass 123456" -p 6379:6379  redis/redis-stack:latest  
docker run --name some-zookeeper --restart always -d zookeeper -p 2181:2181

# eureka
export JAVA_HOME=/usr/local/Cellar/openjdk@17/17.0.6/libexec/openjdk.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
cd /Users/fhdone/Documents/code/workspace/java2022/jan
mvn spring-boot:run

# hystric-dashboard 升级到springboot3后不能使用  因为javax变成了jakarta 
export JAVA_HOME=/usr/local/Cellar/openjdk@17/17.0.6/libexec/openjdk.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
cd /Users/fhdone/Documents/code/workspace/java2022/feb
mvn spring-boot:run

# service
export JAVA_HOME=/usr/local/Cellar/openjdk@17/17.0.6/libexec/openjdk.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
cd /Users/fhdone/Documents/code/workspace/java2022/july
mvn spring-boot:run

# service
export JAVA_HOME=/usr/local/Cellar/openjdk@17/17.0.6/libexec/openjdk.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
cd /Users/fhdone/Documents/code/workspace/java2022/august
mvn spring-boot:run

# client
export JAVA_HOME=/usr/local/Cellar/openjdk@17/17.0.6/libexec/openjdk.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
cd /Users/fhdone/Documents/code/workspace/java2022/june
mvn spring-boot:run

# gateway
export JAVA_HOME=/usr/local/Cellar/openjdk@17/17.0.6/libexec/openjdk.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
cd /Users/fhdone/Documents/code/workspace/java2022/may
mvn spring-boot:run