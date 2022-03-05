# SimpleBookshelf

## Install

Windows

```bat
winget install Microsoft.OpenJDK.17
```

## Usage

```bash
./gradlew bootJar

./gradlew build
java -jar build/libs/
```

## 参考 / メモ

### Spring boot / Kotlin

* [Spring BootでサーバーサイドKotlin入門 - Qiita](https://qiita.com/kawasaki_dev/items/1a188878eb6928880256)
* 公式チュートリアル [Tutorial | Building web applications with Spring Boot and Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin/)
* プロジェクトビルダー [Spring Initializr](https://start.spring.io/)
  * Project: Gradle Project
  * Language: Kotlin
  * Spring Boot: 2.6.4
  * Packaging: Jar
  * Java: 17
  * Dependencies: Spring Web / Thymeleaf / Spring Data JPA / H2 Database
* [チュートリアル: 最初の Kotlin アプリケーションを作成する | IntelliJ IDEA](https://pleiades.io/help/idea/create-your-first-kotlin-app.html)
* [チュートリアル | Kotlin で Spring Boot Web アプリケーションの作成 - 公式サンプルコード](https://spring.pleiades.io/guides/tutorials/spring-boot-kotlin/)
* [とほほのKotlin入門 - とほほのWWW入門](https://www.tohoho-web.com/ex/kotlin.html)
* [数字で見る 2020年における Java の現状 | The IntelliJ IDEA Blog](https://blog.jetbrains.com/ja/idea/2020/10/a-picture-of-java-in-2020-ja/)
* [簡単なWebAPIを作ってみよう編｜SpringBootに入門するための助走本（Zenn改訂版）](https://zenn.dev/sugaryo/books/spring-boot-run-up/viewer/api_controller)
* [Spring Boot JPA で MySQL データアクセス - 公式サンプルコード](https://spring.pleiades.io/guides/gs/accessing-data-mysql/)
* Javaの命名規則 [Code Conventions for the Java Programming Language: 9. Naming Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
* [Kotlin文法 - クラス、継承、プロパティ - Qiita](https://qiita.com/k5n/items/35e76d79ee9de4effb89)
* [Kotlin3日目で気になった仕様を列挙する #love_kotlin - Qiita](https://qiita.com/hiroga/items/4bbab5cce8dc39120c8e)

### Spring Data JPA

* [Spring Data JPAのソート - Qiita](https://qiita.com/parapore/items/4acffd670fc913e05d85)
* [【Spring Data JPA】自動実装されるメソッドの命名ルール - Qiita](https://qiita.com/shindo_ryo/items/af7d12be264c2cc4b252)

### IntelliJ IDEA

* IntelliJ 参考 [Spring Boot | IntelliJ IDEA](https://pleiades.io/help/idea/spring-boot.html)
* [IntelliJ IDEA初期設定（主にエディタ） - Qiita](https://qiita.com/keitakn/items/5968b9eee4177c302481)
* [Gradle ツールウィンドウ | IntelliJ IDEA](https://pleiades.io/help/idea/jetgradle-tool-window.html)

## 便利ツール

* [Name Generator](https://www.name-generator.org.uk/)
