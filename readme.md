# Mixeway OpenVAS REST API

###Informations


Due to problems with using standard TCP Socket which is avaliable after running GVMD process on listening port, we have decided
to release REST API for OpenVAS.

This application is using `GVM-CLI` in order to communicate with unix scocket so it has to be run on the same machine as 
OpenVAS.

### Requirements
1. JAVA 1.8
2. MVN (if installing from sources)
2. OpenVAS installed (https://www.openvas.org/)  
3. OpenVAS tools installed (https://github.com/greenbone/gvm-tools)
4. OpenVAS manager (GVMD) process has to be running on unix socket (path to the socket will be needed)
5. PKCS12 file which will contain 

### Installation
From Sources:
```shell script
git clone https://github.com/mixeway/mixewayopenvasrestapi
cd mixewayopenvasrestapi
mvn package
# If You want to generate self-signed certificates
# ./setup-certs.sh
# Make sure You know location of trust-store or You have generated one
java -jar target/MixewayOpenVasRestAPI-1.0.0-SNAPSHOT.jar \
    --server.port=8443 \
    --server.ssl.key-store=pki/localhost.p12 \
    --server.ssl.key-store-password=qwerty \
    --server.ssl.trust-store=/etc/pki/localhost.jks \ 
    --server.ssl.trust-store-password=qwerty \
    --openvasmd.socket=/usr/local/var/run/openvasmd.sock \
    --allowed.users=localhost
```

From Binary:
```shell script
wget ...
# If You want to generate self-signed certificates download setup scripts or prepare JKS and PKCS12 on YOur own
# Make sure You know location of trust-store or You have generated one
java -jar target/MixewayOpenVasRestAPI-1.0.0-SNAPSHOT.jar \
    --server.port=8443 \
    --server.ssl.key-store=/etc/pki/certificate.p12 \
    --server.ssl.key-store-password=changeit \
    --server.ssl.trust-store=/etc/pki/localhost.jks \ 
    --server.ssl.trust-store-password=changeit \
    --openvasmd.socket=/usr/local/var/run/openvasmd.sock \
    --allowed.users=localhost
```

Parameters for running:

Parameter  | Default Value | Description
------------- | ------------- | -------------
server.port  | 8443 | Port REST API will listen on
server.ssl.key-store  | pki/certificate.p12 | PKCS12 keystore (containing privatekey and certificate) which will be used to encrypt traffic
server.ssl.key-store-password | changeit | password for PKCS12 file
server.ssl.trust-store | /etc/pki/localhost.jks | Java Keystore used to verify if connecting client can be trusted. Mixeway certificate has to be added into this JKS
server.ssl.trust-store-password | changeit | password for JSK file
openvasmd.socket | /usr/local/var/run/openvasmd.sock | path for Unix Socket of OpenVAS Manager (GVMD)
allowed.users | localhost,127.0.0.1 | Common Name of TLS Certificate which is allowed to send signed request to REST API (comma separeted if more needed)

### Authentication
Mixeway OpenVAS REST API in version 1.0.0 is using only TLS Certificate base authentication. Which means only request signed by
trusted TLS Certificate with CN on allow list (allowed.users) can be authenticated. 

Mixeway already signs all outgoing requests with its certificate so make sure that Mixeway's certificate is added to trust store
and allowed.users is set properly.

Authentication on OpenVAS side is made by username/password generated in OpenVAS (through CLI, gmv-tools or GUI).

For Debugging purposes example command:
```shell script
curl --insecure --cert cert.crt --key private.key \
     --header "Content-Type: application/json" \
     --request POST \
     --data '{"username":"openvasadmin","password":"changeit"}' \
     "https://127.0.0.1/initialize"
```

### API Methods
Path  | HTTP Method | Request Body | Description 
------------- | ------------- | ------------- | -------------
/initialize | POST | <pre lang="json">{<br>  "username":"openvasadmin",<br>  "password":"changeit"<br>}</pre> | Testing endpoint for verification if everything works properly
/createtarget | POST | <pre lang="json">{<br>  "user": {<br>    "username":"openvasadmin",<br>    "password":"changeit"<br>  },<br>  "params": {<br>    "name":"test_target",<br>    "hosts":"127.0.0.0/28"<br>  }<br>}</pre> | Method which will create targets with `name` and host values `hosts`
/createtask | POST | <pre lang="json">{<br>  "user": {<br>    "username":"openvasadmin",<br>    "password":"changeit"<br>  },<br>  "params": {<br>    "config_id":"ffffff-ffff-ffff-ffffffffffff",<br>    "target_id":"ffffff-ffff-ffff-ffffffffffff",<br>    "name":"task_name",<br>    "scanner_id":"ffffff-ffff-ffff-ffffffffffff"<br>  }<br>}</pre> | Method which will create taks with given configuration, on particular scan and selected target
/modifytask/{taskId} | POST | <pre lang="json">{<br>  "user": {<br>    "username":"openvasadmin",<br>    "password":"changeit"<br>  },<br>  "params": {<br>    "task_id":"ffffff-ffff-ffff-ffffffffffff",<br>    "target_id":"ffffff-ffff-ffff-ffffffffffff" <br>  }<br>}</pre> | Method which modify target for givent taks
/starttask | POST | <pre lang="json">{<br>  "user": {<br>    "username":"openvasadmin",<br>    "password":"changeit"<br>  },<br>  "params": {<br>    "task_id":"ffffff-ffff-ffff-ffffffffffff"  <br>  }<br>}</pre> | Starting task by task_id
/checktask | POST | <pre lang="json">{<br>  "user": {<br>    "username":"openvasadmin",<br>    "password":"changeit"<br>  },<br>  "params": {<br>    "task_id":"ffffff-ffff-ffff-ffffffffffff"  <br>  }<br>}</pre> | Checking status of task (Running, completed)
/getreport | POST | <pre lang="json">{<br>  "user": {<br>    "username":"openvasadmin",<br>    "password":"changeit"<br>  },<br>  "params": {<br>    "report_id":"ffffff-ffff-ffff-ffffffffffff"  <br>  }<br>}</pre> | Getting vulnerabilities from OpenVAS by report_id
