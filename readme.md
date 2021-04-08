#### Sample Spring Native Application to explore reactive support.

###### Application accesses GitHub, DevTo & StackOverflow api's to fetch user specific informations.

###### Exposed API's can be consumed to build a social dashboard.

###### All endpoints are reactive.

```
# fetch StackOverflow data
@GetMapping(value = "/sov", produces = MediaType.TEXT_EVENT_STREAM_VALUE)

# fetch github data
@GetMapping(value = "/gith", produces = MediaType.TEXT_EVENT_STREAM_VALUE)

# fetch DevTo data
@GetMapping(value = "/devto", produces = MediaType.TEXT_EVENT_STREAM_VALUE)

```
