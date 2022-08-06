<!DOCTYPE html>
<html lang="en">
<head>
    <title>Code</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>

<body>
<span id="load_date">${code.date}</span>
<#if (code.time>0)>
    <span id="time_restriction">The code will be available for ${code.time} seconds.</span>
</#if>
<#if (code.views>0)>
    <span id="views_restriction">${code.views-1} more views allowed.</span>
</#if>

<pre id="code_snippet"><code>${code.code}</code></pre>

</body>
</html>