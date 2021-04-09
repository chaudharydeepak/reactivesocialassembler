#### Sample Spring Native Application to explore reactive support.

###### Application accesses GitHub, DevTo & StackOverflow api's to fetch user specific informations.

###### Exposed API's can be consumed to build a social dashboard.

###### All endpoints are reactive.

###### Include Oracle R2DBC driver with Spring R2DBC impl.

```
# fetch StackOverflow data
@GetMapping(value = "/sov", produces = MediaType.TEXT_EVENT_STREAM_VALUE)

# fetch github data
@GetMapping(value = "/gith", produces = MediaType.TEXT_EVENT_STREAM_VALUE)

# fetch DevTo data
@GetMapping(value = "/devto", produces = MediaType.TEXT_EVENT_STREAM_VALUE)

.....
public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {
}
.....
```

```
# generate native image with graalvm plugin [ you can use spring boot native image plugin as well ].
$ mvn clean -Pnative-image package
```

- ###### Plain spring-boot run takes over 1 sec vs native image starts in about ~300ms.
  ```
  2021-04-07 22:49:03.773  INFO 13774 --- [           main] c.s.t.assembler.AssemblerApplication     : Started AssemblerApplication in 0.315 seconds (JVM running for 0.316)
  ```
- ###### Native image can be further compressed:
  ```
  $ upx -7 -k com.social.techblog.assembler.assemblerapplication
  ```