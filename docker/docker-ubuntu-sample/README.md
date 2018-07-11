ubuntu:16.04 설치

참조 : http://thebluecheese.tistory.com/22


master : testubuntu1

slave : testubuntu2, testubuntu3

ssh : root/root


##########################################################################################


1. [master, slave] default setup

apt-get update -y

apt-get upgrade -y

apt-get install vim -y


2. [master, slave] install rabbitmq 

apt-get install -y erlang

apt-get install rabbitmq-server -y

systemctl enable rabbitmq-server

ps -ef | grep rabbitmq

3. [master, slave] start rabbitmq 

service rabbitmq-server start


4. [master] set cookie (모든 클러스터 노드에는 동일한 쿠키가 있어야함)

cd /var/lib/rabbitmq

scp .erlang.cookie root@testubuntu2:/var/lib/rabbitmq

scp .erlang.cookie root@testubuntu3:/var/lib/rabbitmq

[slave]에 권한이 없는 경우 chmod 777 .erlang.cookie 해서 올리고 다시 chmod 400 .erlang.cookie


5. [slave] set host (반드시 [master]의 hostname을 사용)

vi /etc/hosts

172.21.0.*      testubuntu1


6. [master, slave] restart rabbitmq 

service rabbitmq-server stop

service rabbitmq-server start


rabbitmqctl cluster_status



##########################################################################################
##########################################################################################
##########################################################################################


10.0.2.15
ssh ksb@10.0.2.4
ssh ksb@10.0.2.5


[master,slave]
sudo service rabbitmq-server stop
sudo service rabbitmq-server start

sudo rabbitmqctl cluster_status
sudo rabbitmqctl status

[slave]
cd /var/lib/rabbitmq
sudo chmod 777 .erlang.cookie

[master]
cd /var/lib/rabbitmq
sudo scp .erlang.cookie ksb@10.0.2.4:/var/lib/rabbitmq
sudo scp .erlang.cookie ksb@10.0.2.5:/var/lib/rabbitmq

[slave]
sudo chmod 400 .erlang.cookie

[slave]
sudo vi /etc/hosts
10.0.2.15       testubuntu1

[slave]
sudo service rabbitmq-server stop
sudo service rabbitmq-server start

sudo rabbitmqctl cluster_status

sudo rabbitmqctl stop_app

sudo rabbitmqctl join_cluster --ram rabbit@testubuntu1

sudo rabbitmqctl cluster_status


[master]
sudo rabbitmqctl cluster_status

#######################################################




