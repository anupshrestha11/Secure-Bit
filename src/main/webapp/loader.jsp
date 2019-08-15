<html>


<head>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.js"></script>
    <script>

        $(window).load(function () {

            $(".se-pre-con").fadeOut("slow");
        });


    </script>
    <style>

        .no-js #loader {
            display: none;
        }

        .js #loader {
            display: block;
            position: absolute;
            left: 100px;
            top: 0;
        }

        .se-pre-con {
            position: fixed;
            left: 0px;
            top: 0px;
            width: 100%;
            height: 100%;
            z-index: 9999;
            background: url(/loading.svg) center no-repeat #fff;
        }

    </style>

    <title></title>
</head>
<body>

<div class="se-pre-con"></div>


</body>

</html>