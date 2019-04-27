package net.entelijan.tf.slider

object SliderTemplate {

  def glide(name: String, files: Seq[String], tilesDim: TilesDim): String = {
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
       |            background-image: url(images/tiles/tiles$name.${tilesDim.imgType.ext});
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
       |            visibillity: hidden;
       |        }
       |    </style>
       |</head>
       |<body>
       |<div id="slider1" class="glide">
       |    <div id="closeb" onclick="hideSlider();"></div>
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
       |<script src="js/taschenfahrrad.js"></script>
       |<script>
       |  var glide = new Glide('.glide', {
       |    type: 'carousel',
       |    startAt: 0,
       |    perView: 1,
       |    gap: 0
       |  }).mount();
       |  var tiles_length = ${files.size};
       |  var tiles_cols = ${tilesDim.cols};
       |  var tiles_tileSize = ${tilesDim.tileSize - tilesDim.borderSize};
       |</script>
       |</body>
       |</html>
    """.stripMargin
  }

}
