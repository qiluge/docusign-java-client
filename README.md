# The Official DocuSign Java Client

[![Build status][travis-image]][travis-url]
[![Maven Central status][maven-image]][maven-url]

## Requirements

- Java 1.7+
- Free [Developer Sandbox](https://go.docusign.com/sandbox/productshot/?elqCampaignId=16531)

## Compatibility

- Java 1.9+

## Note

This open-source SDK is provided for cases where you would like to make additional changes that the SDK does not provide out-of-the-box. If you simply want to use the SDK with any of the examples shown in the [Developer Center](https://developers.docusign.com/esign-rest-api/code-examples), follow the installation instructions below.

## Installation

Note: DocuSign uses **Eclipse** with **Maven** for testing purposes.

### Maven:

1. In Eclipse, create a new project by selecting **File** -> **New** -> **Project**.
2. In the **New Project Wizard** , expand **Maven** , then select **Maven Project.**
3. Leave **Create a simple project** unchecked.
4. Select **Next** , then provide a unique **Group** and **Artifact Id**.
5. In the directory where you've saved your project, open the _pom.xml_ file.
6. In the _pom.xml_ file, locate the **dependencies** node.
7. Add:

```
<dependency>
  <groupId>com.docusign</groupId>
  <artifactId>docusign-esign-java</artifactId>
  <version>3.7.0-BETA</version>
</dependency>
```

8. If your project is still open, restart **Eclipse**.

## Dependencies

This client has the following external dependencies:

- io.swagger:swagger-annotations:jar:1.5.17
- org.glassfish.jersey.core:jersey-client:jar:2.29.1
- org.glassfish.jersey.media:jersey-media-multipart:jar:2.29.1
- org.glassfish.jersey.media:jersey-media-json-jackson:2.29.1
- com.fasterxml.jackson.core:jackson-core:jar:2.10.1
- com.fasterxml.jackson.core:jackson-annotations:jar:2.10.1
- com.fasterxml.jackson.core:jackson-databind:2.10.1
- com.fasterxml.jackson.datatype:jackson-datatype-joda:jar:2.10.1
- com.brsanthu:migbase64:2.2
- junit:junit:jar:4.12
- com.apache.oltu.oauth2:org.apache.oltu.oauth2.client:1.0.2
- com.auth0:java-jwt:3.4.1
- org.bouncycastle:bcprov-jdk15on:1.60

## Code Examples

### Launchers

DocuSign provides a sample application code referred to as a [Launcher](https://github.com/docusign/code-examples-java). The Launcher contains a set of 31 common use cases and associated source files. These examples use either DocuSign&#39;s [Authorization Code Grant](https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant) or [JSON Web Tokens (JWT)](https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-jsonwebtoken) flows.

### Proof-of-concept applications

If your goal is to create a proof-of-concept application, DocuSign provides a set of [Quick Start](https://github.com/docusign/qs-java) examples. The Quick Start examples are meant to be used with DocuSign's [OAuth Token Generator](https://developers.docusign.com/oauth-token-generator), which will allow you to generate tokens for the Demo/Sandbox environment only. These tokens last for eight hours and will enable you to build your proof-of-concept application without the need to fully implement an OAuth solution.

## OAuth Implementations

For details regarding which type of OAuth grant will work best for your DocuSign integration, see the [REST API Authentication Overview](https://developers.docusign.com/esign-rest-api/guides/authentication) guide located on the [DocuSign Developer Center](https://developers.docusign.com/esign-rest-api/guides/authentication).

For security purposes, DocuSign recommends using the Authorization Code Grant flow.

There are other use-case scenarios, such as **single page applications** (SPA) that use **Cross-Origin Resource Sharing** (CORS), or where there may not be a user to interact with your Service Account. For these use cases, DocuSign also supports [JWT](https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-jsonwebtoken) and [Implicit](https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-implicit) grants. For code examples, see the links below:

- [JWT (JSON Web Token)](https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-jsonwebtoken)
- [Implicit Grant] (https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-implicit)

## Support

Log issues against this client through GitHub. We also have an [active developer community on Stack Overflow](https://stackoverflow.com/questions/tagged/docusignapi).

## Ontology

Commit document hash to Ontology blockchain after signature completed.

![img](process.png)

## License

The DocuSign Java Client is licensed under the [MIT License](https://github.com/docusign/docusign-java-client/blob/master/LICENSE).


[travis-image]: https://img.shields.io/travis/docusign/docusign-java-client.svg?style=flat
[travis-url]: https://travis-ci.org/docusign/docusign-java-client
[maven-image]: https://img.shields.io/maven-central/v/com.docusign/docusign-esign-java.svg?style=flat
[maven-url]: https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.docusign%22

### Additional Resources
* [DocuSign Developer Center](https://developers.docusign.com)
* [DocuSign API on Twitter](https://twitter.com/docusignapi)
* [DocuSign For Developers on LinkedIn](https://www.linkedin.com/showcase/docusign-for-developers/)
* [DocuSign For Developers on YouTube](https://www.youtube.com/channel/UCJSJ2kMs_qeQotmw4-lX2NQ)