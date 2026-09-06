# rar-utilities

Skeletal base classes that take some of the boilerplate out of writing a
Jakarta Connectors (JCA) resource adapter — the `ResourceAdapter`,
`ManagedConnection`, `ManagedConnectionFactory` and CCI plumbing that every
adapter has to provide but nobody enjoys writing twice.

> ### ⚠️ Archived — not maintained
>
> This repository is archived and will receive no further releases or fixes.
> The final state builds against Jakarta EE 11 on Java 25, and the
> specification deviations that would have broken a real adapter have been
> corrected. What remains is listed under [Known issues](#known-issues).

## Why it was archived

Jakarta Connectors itself is not dead — Connectors 2.2 is listed for Jakarta
EE 12 and a 3.0 revision is in development. The ecosystem around it is what
moved on:

- Connectors is a **Full Platform** specification. It is absent from the Core
  and Web Profiles, and from Quarkus, Helidon and Micronaut entirely. That
  leaves WildFly (full), Payara, Open Liberty and WebSphere.
- **Spring 6 removed its JCA CCI support** outright.
- Writing a *new* resource adapter is now a rare event. Most JCA in the wild
  is consuming vendor adapters (IBM MQ, CICS, SAP JCo); new EIS integration
  goes over REST, Kafka or gRPC.
- What remains here is thin. The genuinely useful part is the connection
  event plumbing and the metadata records — perhaps 150 lines. The hard parts
  of adapter development get no help at all: work management, inbound
  endpoint activation, transaction support (`LocalTransaction` / `XAResource`),
  credential extraction from a `Subject`, and connection matching are all
  absent.

## What is in it

| Package                     | Contents                                                                                                                                                                                           |
|-----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `io.github.ralfspoeth.raru` | `AbstractResourceAdapter` (a `Serializable` `ResourceAdapter` that just holds the `BootstrapContext`), `DefaultConnectionManager` (a non-pooling `ConnectionManager` for non-managed environments) |
| `…raru.outbound`            | `AbstractManagedConnection<T>` (listener registration and `ConnectionEvent` dispatch), `AbstractManagedConnectionFactory`, `DefaultOutboundResourceAdapter`, `DefaultManagedConnectionMetaData`    |
| `…raru.cci`                 | `AbstractCCIConnection`, `AbstractCCIConnectionFactory`, `DefaultConnectionMetaData`                                                                                                               |
| `…raru.ci`                  | `ConnectionFactory` and `AbstractConnectionFactory` for non-CCI adapters                                                                                                                           |
| `…raru.inbound`             | Empty; a placeholder should support for inbound adapters ever be added                                                                                                                             |

## Known issues

The repository is a snapshot, not a product, and it carries **no tests** —
nothing here has been exercised against a running container.

The defects found during the final review have been fixed:

- `DefaultConnectionManager.allocateConnection` returns an application-level
  connection handle rather than the `ManagedConnection` itself, and destroys
  the managed connection again if the handle cannot be obtained, so a failed
  handoff no longer leaks a physical connection.
- `AbstractManagedConnectionFactory.matchManagedConnections` returns `null`
  for the no-match case instead of throwing `NotSupportedException`, so an
  ordinary pool miss is no longer a failure.
- `AbstractConnectionSpec` was removed. It was an empty marker class that cost
  subclasses their one inheritance slot to supply `Serializable`, sat in the
  wrong package (a CCI `ConnectionSpec` is an outbound concept, not an inbound
  one), and carried a javadoc example that would not compile. The `inbound`
  package itself is kept as a documented placeholder; this library is
  outbound-only, as
  `DefaultOutboundResourceAdapter.endpointActivation` throwing
  `NotSupportedException` makes plain.
- Serialization was made sound. `ManagedConnectionFactory` extends
  `Serializable`, so `logWriter` and `ra` are now `transient` — the container
  re-supplies both after deserialization — and the class carries a
  `serialVersionUID`. `AbstractManagedConnection` no longer declares
  `Serializable`, which `ManagedConnection` never required and which it could
  not have honoured anyway.

## Requirements

- Java 25 (`maven.compiler.release` 25)
- Jakarta EE 11 — `jakarta.resource-api` 2.1, versions managed by
  `jakarta.platform:jakarta.jakartaee-bom:11.0.0`
- JPMS module name `io.github.ralfspoeth.raru`

## Building

```
mvn clean install
```

The build inherits from `io.github.ralfspoeth:plumbum:3.0.3`.

The last tagged release is `rar-utilities-1.0.2`, published under the former
`com.pd.spr` coordinates. Version 2.0.0 was never released; the package rename
to `io.github.ralfspoeth.raru` and the move to Jakarta EE 11 exist only here in
source form.

## Writing an adapter today

If you actually need a resource adapter, start from the
[Jakarta Connectors specification](https://jakarta.ee/specifications/connectors/)
and your application server's own adapter examples, which are maintained and
cover the inbound and transactional contracts this library never did.

## License

MIT. Copyright 2011–2026 Ralf Spöth.
