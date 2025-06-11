# Protocol Overview

Messages consist of a start line followed by headers and an optional body.
Lines are separated by CRLF (`\r\n`).

Example:
```
GET /download?file=foo.txt
Content-Length: 100
Checksum: abc123

<bytes>
```
