package net.entelijan.tf.slider

object SliderTemplate {

  def slick(name: String, files: Seq[String]): String = {
    val imgsl: String = files.map { fn =>
      s"""<div class="fill" style="background-image: url('images/$name/$fn')"></div>"""
    }.mkString("\n")

    s"""
       |<!DOCTYPE html>
       |<html>
       |<head>
       |  <title>taschenfahrrad $name</title>
       |  <meta charset="UTF-8">
       |  <link rel="stylesheet" type="text/css" href="./slick/slick.css">
       |  <link rel="stylesheet" type="text/css" href="./slick/slick-theme.css">
       |  <style type="text/css">
       |    html, body {
       |      margin: 0;
       |      padding: 0;
       |    }
       |    .slick-slider {
       |        z-index: 10;
       |    }
       |    .fill {
       |        width: 100vw;
       |        height: 100vh;
       |        background-position: center;
       |        background-size: contain;
       |        background-repeat: no-repeat;
       |        background-color: #000000d1;
       |     }
       |     #back {
       |         width: 100vw;
       |         height: 100vh;
       |         background-image: url(images/index/tilesindex.jpg);
       |         z-index: 0;
       |         position: absolute;
       |         top: -10px;
       |     }
       |  </style>
       |</head>
       |<body>
       |  <section class="lazy slider" data-sizes="90vw">
       |$imgsl
       |  </section>
       |  <div id="back"></div>
       |  <script src="https://code.jquery.com/jquery-2.2.0.min.js" type="text/javascript"></script>
       |  <script src="./slick/slick.js" type="text/javascript" charset="utf-8"></script>
       |  <script type="text/javascript">
       |     $$(document).on('ready', function() {
       |       $$(".lazy").slick({
       |        lazyLoad: 'ondemand', // ondemand progressive anticipated
       |        infinite: true
       |      });
       |    });
       |</script>
       |</body>
       |</html>
       |
    """.stripMargin
  }

  def owl(name: String, files: Seq[String]): String = {
    val imgs: String = files.map { fn =>
      s"""<div><div class="fill" style="background-image: url('images/$name/$fn')"></div></div>"""
    }.mkString("\n")

    s"""
       |<!DOCTYPE html>
       |<html lang="en">
       |  <head>
       |    <meta charset="utf-8">
       |    <meta name="msapplication-tap-highlight" content="no" />
       |    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
       |    <meta name="description" content="autoHeight usage demo">
       |    <title>owl</title>
       |    <link rel="stylesheet" href="owlcarousel/assets/owl.carousel.min.css">
       |    <link rel="stylesheet" href="owlcarousel/assets/owl.theme.default.min.css">
       |    <script src="owlcarousel/assets/vendors/jquery.min.js"></script>
       |    <script src="owlcarousel/owl.carousel.min.js"></script>
       |    <style>
       |            body {
       |            margin: 0px;
       |        }
       |        .fill {
       |            width: 100vw;
       |            height: 100vh;
       |            background-position: center;
       |            background-size: contain;
       |            background-repeat: no-repeat;
       |            background-color: #000000d1;
       |            z-index: 10;
       |        }
       |        #back {
       |            width: 100vw;
       |            height: 100vh;
       |            background-image: url(images/index/tilesindex.jpg);
       |            z-index: 0;
       |            position: absolute;
       |            top: 0px;
       |        }
       |        #back {
       |            width: 100vw;
       |            height: 100vh;
       |            background-image: url(images/index/tilesindex.jpg);
       |            z-index: 0;
       |            position: absolute;
       |            top: 0px;
       |        }
       |    </style>
       |  </head>
       |  <body>
       |  <div class="owl-carousel">
       |  $imgs
       |  </div>
       |  <div id="back"></div>
       |  <script>
       |     $$('.owl-carousel').owlCarousel({
       |      items:1,
       |      margin:10
       |    });
       |  </script>
       |  </body>
       |</html>
    """.stripMargin
  }

  def glide(name: String, files: Seq[String]): String = {
    val imgs: String = files.map { fn =>
      s"""            <li class="glide__slide"><div class="fill" style="background-image: url(images/$name/$fn);"></div></li>"""
    }.mkString("\n")

    s"""<!DOCTYPE html>
       |<html lang="en">
       |<head>
       |    <meta charset="UTF-8">
       |    <title>tf glide</title>
       |    <link rel="stylesheet" href="glide/css/glide.core.min.css">
       |    <link rel="stylesheet" href="glide/css/glide.theme.min.css">
       |    <style>
       |        body {
       |            margin: 0;
       |        }
       |        .fill {
       |            width: 100vw;
       |            height: 100vh;
       |            background-position: center;
       |            background-size: contain;
       |            background-repeat: no-repeat;
       |            background-color: #00000090;
       |        }
       |        #back {
       |            width: 100vw;
       |            height: 100vh;
       |            background-image: url(images/tiles/tiles$name.jpg);
       |            position: absolute;
       |            top: 0;
       |            z-index: 0;
       |        }
       |        .glide {
       |            z-index: 10;
       |        }
       |        .glide__slides {
       |            margin: 0;
       |        }
       |        #closeb {
       |            background-image: url(css/cross.png);
       |            background-repeat: no-repeat;
       |            position: absolute;
       |            top: 10px;
       |            left: 10px;
       |            width:150px;
       |            height: 150px;
       |            z-index: 30;
       |            cursor: pointer;
       |        }
       |        .butto-left {
       |            background-image: url(css/arrow-left.png);
       |            left: 10px;
       |        }
       |        .butto-right {
       |            background-image: url(css/arrow-right.png);
       |            right: 10px;
       |        }
       |        .butto {
       |            position: absolute;
       |            display: block;
       |            top: 50%;
       |            z-index: 2;
       |            width: 50px;
       |            height: 50px;
       |            cursor: pointer;
       |            background-color: rgba(255,255,255,0);
       |            border-width: 0;
       |            outline: none;
       |        }
       |        #slider1 {
       |            display: block;
       |        }
       |    </style>
       |</head>
       |<body>
       |<div id="slider1" class="glide">
       |    <div id="closeb" onclick="console.log('close');"></div>
       |    <div class="glide__track" data-glide-el="track">
       |        <ul class="glide__slides">
       |$imgs
       |        </ul>
       |    </div>
       |    <div class="glide__arrows" data-glide-el="controls">
       |        <button class="butto butto-left" data-glide-dir="<"></button>
       |        <button class="butto butto-right" data-glide-dir=">"></button>
       |    </div>
       |</div>
       |<div id="back"></div>
       |<script src="glide/glide.min.js"></script>
       |<script>
       |  new Glide('.glide', {
       |  type: 'carousel',
       |  startAt: 0,
       |  perView: 1,
       |  gap: 0
       |}).mount();
       |</script>
       |</body>
       |</html>
    """.stripMargin
  }

}
