# Simple Java HTTP Proxy

Starter template for Internet Engineering Course HW#1 assignment (Dr.Bakhshi - Spring 2018) 

It is recommended to use [curl](https://curl.haxx.se/download.html) HTTP client to test the proxy server:

```bash
curl -v -x http://localhost:8080 http://example.com
```

## Examples of working URLs:

- http://example.com
- http://icanhazip.com
- http://www.squid-cache.org
- http://www.time.ir (UTF8 not supported!)

## What to implement?

- Complete implementation of [HTTPResponse](https://github.com/pi0/http-proxy-java/blob/master/src/HTTPResponse.java)
- Complete implementation of [HTTPRequest](https://github.com/pi0/http-proxy-java/blob/master/src/HTTPRequest.java#L46) 
- [Analyze](https://github.com/pi0/http-proxy-java/blob/master/src/ProxyThread.java#L33) HTTPRequest
- [Analyze](https://github.com/pi0/http-proxy-java/blob/master/src/ProxyThread.java#L41) HTTPResponse
- Implement A way for providing list of sites to monitor. (Using file)
- Log monitor requests to a file
- (optional) Add a GUI/TUI

# License

No-License!
