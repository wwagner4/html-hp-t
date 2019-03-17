package net.entelijan.tf.slider

object SliderTemplate {

  def index(name: String, files: Seq[String]): String = {
    val imgsl: String = files.map { fn =>
      s"""<div>
         |<img data-lazy="images/$name/$fn" data-srcset="images/$name/$fn, images/$name/$fn" data-sizes="100vw">
         |</div>
      """.stripMargin
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
       |
       |    * {
       |      box-sizing: border-box;
       |    }
       |
       |    .slider {
       |        width: 50%;
       |        margin: 100px auto;
       |    }
       |
       |    .slick-slide {
       |      margin: 0px 20px;
       |    }
       |
       |    .slick-slide img {
       |      width: 100%;
       |    }
       |
       |    .slick-prev:before,
       |    .slick-next:before {
       |      color: black;
       |    }
       |
       |
       |    .slick-slide {
       |      transition: all ease-in-out .3s;
       |      opacity: .2;
       |    }
       |
       |    .slick-active {
       |      opacity: .5;
       |    }
       |
       |    .slick-current {
       |      opacity: 1;
       |    }
       |  </style>
       |</head>
       |<body>
       |  <section class="lazy slider" data-sizes="90vw">
       |$imgsl
       |  </section>
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

}
