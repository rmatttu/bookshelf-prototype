# SimpleBookshelf

## Install

### docker

下記コマンドを実行

```
git clone https://github.com/rmatttu/simple-bookshelf.git
cd simple-bookshelf
docker-compose up
```

次にアクセス http://localhost:8080

### ビルド&実行

```bash
git clone https://github.com/rmatttu/simple-bookshelf.git
cd simple-bookshelf
./gradlew build

# h2databaseを使用し起動します
java -jar build/libs/simple-bookshelf-0.0.1-SNAPSHOT.jar
```

## 環境

* プロジェクトビルダー [Spring Initializr](https://start.spring.io/) を使用して作成
  * Project: Gradle Project
  * Language: Kotlin
  * Spring Boot: 2.6.4
  * Packaging: Jar
  * Java: 17
  * Dependencies: Spring Web / Thymeleaf / Spring Data JPA / H2 Database
