# Pokedex v3
# Documentacion para despliegue
      1. En Heroku-cli inciar sesion con nuestra cuenta de Heroku con el comando "heroku login"
      2. Intsalar el plugin en heroku con el comando "heroku plugins:install heroku-cli-deploy"
      3. Crear nuestra app con el siguiente comando "heroku create --no-remote" y nos dará como resultado lo siguiente Creating app... done, ⬢ fast-waters-63824
          https://fast-waters-63824.herokuapp.com/ | https://git.heroku.com/fast-waters-63824.git
      4. Desplegar nuestra aplicación con el siguiente comando "heroku deploy:jar Pokedex-0.0.1-SNAPSHOT.jar --app fast-waters-63824" y podremos acceder a nuestra                aplicacion en la siguiente url : https://fast-waters-63824.herokuapp.com/ 
