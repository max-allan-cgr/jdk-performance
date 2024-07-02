Rough steps followed:

```
git clone https://github.com/openjdk/jmh
cd jmh
mvn clean verify
# expect it to fail!
cd jmh-samples
java -jar target/benchmarks.jar JMHSample_12
```

To run in AWS, on Linux 2024:
```
sudo -i
yum -yq install docker
systemctl enable --now docker

usermod -g docker ec2-user
usermod -g docker ssm-user

logout # from root
newgrp docker # (or logout/login)
```

Then:
```
docker build -t public -f Dockerfile.alpine

docker build -t chain -f Dockerfile
```

When a build is done, run it and run the benchmark like this:
```
docker run -it <image> 
cd jmh-samples
java -jar target/benchmarks.jar JMHSample_40
```

All the other sample benchmarks are available to be run as well, pick a number!
