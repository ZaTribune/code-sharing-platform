<!DOCTYPE html>
<html lang="en">
<head>
    <title>Latest</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>

<body>
<div id="content">

    <#list codes as code>
        <div style="display: block">
            <span id='load_date'>${code.date}</span>
            <pre id="code_snippet"><code>${code.code}</code></pre>
        </div>
    </#list>
</div>

</body>

</html>