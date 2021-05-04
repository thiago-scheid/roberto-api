wget ""
mv ./linux-amd64

VERSION=$(mvn -q com.smartcodeltd:release-candidate-maven-plugin:LATEST:version -DoutputTemplate="{{ version }}")​

chmod +x ./all

echo "publish app $2 $VERSION in cluster $1"
./all version
./all config set-cluster server-deploy --server $1 --tls
./all config use-cluster server-deploy

echo $DEPLOY_PASSWORD | ./all login --user $DEPLOY_USER

mkdir package

mv pom.xml package
mv Procfile package
mv target/*.jar package
mv system.properties package

echo "env $3"

echo "files to publish ..."
ls package -al

echo "app info ..."

./all app info $2
./all deploy create package/. --app $2 --no-input --description $VERSION
