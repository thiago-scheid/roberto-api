wget https://github.com/luizalabs/teresa/releases/download/v0.31.0/teresa-linux-amd64
mv ./teresa-linux-amd64 teresa

VERSION=$(mvn -q com.smartcodeltd:release-candidate-maven-plugin:LATEST:version -DoutputTemplate="{{ version }}")​

chmod +x ./teresa

echo "publish app $2 $VERSION in cluster $1"
./teresa version
./teresa config set-cluster server-deploy --server $1 --tls
./teresa config use-cluster server-deploy

echo $TERESA_PASSWORD | ./teresa login --user $TERESA_USER

mkdir package

mv pom.xml package
mv Procfile package
mv target/*.jar package
mv system.properties package

echo "env $3"

echo "files to publish ..."
ls package -al

echo "app info ..."

./teresa app info $2
./teresa deploy create package/. --app $2 --no-input --description $VERSION
