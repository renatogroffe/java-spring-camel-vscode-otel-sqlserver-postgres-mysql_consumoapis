# java-spring-camel-vscode-otel-sqlserver-postgres-mysql_consumoapis
Exemplo de aplicação criada com Java + Spring + Apache Camel e utilizando Distributed Tracing com Jaeger + OpenTelemetry (configurando porta do Collector) e consumindo APIs REST (uma destas depende de bases SQL Server + PostgreSQL + MySQL). Contém referências para o uso de Docker Compose em ambientes que dependam do OpenTelemetry Collector.

APIs REST utilizadas por este projeto:
- [**Contagem de acessos (.NET 9 + ASP.NET Core)**](https://github.com/renatogroffe/aspnetcore9-otel-jaeger-postgres-mysql_apicontagem)
- [**Saudações (Node.js)**](https://github.com/renatogroffe/nodejs-otel-jaeger_apisaudacoes)
- [**Consumer da API de Contagem (Python)**](https://github.com/renatogroffe/python-otel_apiconsumocontagem)

Agents Java do OpenTelemetry: **https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases**
