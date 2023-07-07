brew services start mysql
/Users/fhdone/dev/redis-6.0.9/src/redis-server /Users/fhdone/dev/redis-6.0.9/redis.conf


brew services stop mysql

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