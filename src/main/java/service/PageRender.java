package service;

import model.Seat;

import java.util.ArrayList;

public class PageRender {
    public static String index(ArrayList<Seat> dataServer) {
        StringBuilder html = new StringBuilder("""
                <!DOCTYPE html>
                <html lang="pt-br">
                  <head>
                    <meta charset="UTF-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                    <link rel="preconnect" href="https://fonts.googleapis.com" />
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
                    <link
                      href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua:wght@400;700&display=swap"
                      rel="stylesheet"
                    />
                    <title>The New York Shows</title>
                  </head>
                  <style>
                    * {
                      margin: 0;
                      padding: 0;
                      font-family: "Inknut Antiqua", serif;
                      font-weight: 400;
                      font-size: 14px;
                    }
                                
                    header {
                      background-color: rgb(0, 2, 29);
                      color: white;
                      text-align: center;
                      font-size: 36px;
                      width: 100%;
                      padding: 10px 0;
                    }
                                
                    main {
                      background-color: rgb(0, 2, 29);
                      color: white;
                      width: fit-content;
                      margin: 30px auto;
                      border-radius: 50px;
                      text-align: center;
                    }
                                
                    main h1 {
                      font-size: 24px;
                    }
                                
                    .seats {
                      display: grid;
                      grid: repeat(3, 225px) / auto-flow 300px;
                      color: rgb(0, 2, 29);
                      padding: 0 25px;
                    }
                                
                    .seats div {
                      width: 250px;
                      height: 200px;
                      background-color: white;
                      margin: 0 25px;
                      border-radius: 25px;
                    }
                                
                    .seats div h2 {
                      text-align: left;
                      margin-left: 10px;
                    }
                                
                    .seats div h3 {
                      color: rgb(0, 63, 0);
                      height: 90px;
                      margin-top: 20px;
                    }
                                
                    .seats div button {
                      width: 60%;
                      border-radius: 25px;
                      background-color: rgb(0, 4, 55);
                      color: white;
                      cursor: pointer;
                      border: none;
                    }
                                
                    .seats div button:hover {
                      background-color: rgb(0, 5, 82);
                    }
                                
                    .seats .reserved h3 {
                      color: black;
                    }
                                
                    .seats .reserved button {
                      width: 60%;
                      border-radius: 25px;
                      background-color: rgb(90, 90, 90);
                      color: white;
                      cursor: default;
                      border: none;
                    }
                                
                    .seats .reserved button:hover {
                      background-color: rgb(90, 90, 90);
                    }
                  </style>
                  <body>
                    <header>The New York Shows</header>
                    <main>
                      <h1>Black Sabbath Fury</h1>
                      <div class="seats">
                        """);
        for (Seat data : dataServer){
            if (data.isReserved()){
                html.append("<div  class='reserved'>")
                        .append("<h2>").append(data.getSlug()).append("</h2>")
                        .append("<h3>Reservado por:<br />").append(data.getReservedBy()).append("</h3>")
                        .append("<button>").append(data.getDateTime()).append("</button>")
                        .append("</div>");
            } else {
                html.append("<div>")
                        .append("<h2>").append(data.getSlug()).append("</h2>")
                        .append("<h3>Disponível</h3>")
                        .append("<a href='/reserve?id=").append(data.getSlug()).append("'>")
                        .append("<button>Reservar</button></a>")
                        .append("</div>");
            }
        }
        html.append("""
                              </div>
                            </main>
                          </body>
                        </html>            
                        """);
        return html.toString();
    }

    public static String reserve(String slug) {
        StringBuilder html = new StringBuilder("""
                <!DOCTYPE html>
                <html lang="pt-br">
                  <head>
                    <meta charset="UTF-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                    <link rel="preconnect" href="https://fonts.googleapis.com" />
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
                    <link
                      href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua:wght@400;700&display=swap"
                      rel="stylesheet"
                    />
                    <title>The New York Shows</title>
                  </head>
                  <style>
                    * {
                      margin: 0;
                      padding: 0;
                      font-family: "Inknut Antiqua", serif;
                      font-weight: 400;
                      font-size: 14px;
                    }
                                
                    header {
                      background-color: rgb(0, 2, 29);
                      color: white;
                      text-align: center;
                      font-size: 36px;
                      width: 100%;
                      padding: 10px 0;
                    }
                                
                    main {
                      background-color: rgb(0, 2, 29);
                      color: white;
                      width: fit-content;
                      margin: 30px auto;
                      border-radius: 50px;
                      text-align: center;
                      padding: 30px 50px;
                    }
                                
                    main h1 {
                      font-size: 24px;
                    }
                                
                    main h2 {
                      font-size: 18px;
                      margin: 0 10px;
                    }
                                
                    input {
                      border-radius: 25px;
                      border: none;
                      width: 100%;
                      padding-left: 10px;
                    }
                                
                    button {
                      width: 30%;
                      border-radius: 25px;
                      background-color: rgb(0, 85, 0);
                      margin-top: 15px;
                      color: white;
                      cursor: pointer;
                      border: none;
                    }
                                
                    button:hover {
                      background-color: rgb(0, 100, 0);
                    }
                     
                    span {
                      display: inline-block;
                      width: 125px;
                      border-radius: 25px;
                      background-color: rgb(70, 70, 70);
                      margin-top: 15px;
                      color: white;
                      cursor: pointer;
                      border: none;
                    }
                
                    span:hover {
                      background-color: rgb(90, 90, 90);
                    }
                  </style>
                  <body>
                    <header>The New York Shows</header>
                    <main>
                      <h1>Black Sabbath Fury</h1>
                      """);
        html.append("<h2>A reserva da poltrona ")
                .append(slug)
                .append(" vai ser no nome de:</h2>")
                .append(""" 
                        <form action="/make-reserve" method="get">
                        <input type="hidden" name="id" id="id" value='""")
                .append(slug)
                .append("'/>")
                .append("""
                        <input type="text" name="name" id="name" maxlength="25" required />
                        <a href="/"><span>Cancelar</span></a>
                        <button type="submit">Reservar</button>
                      </form>
                    </main>
                  </body>
                </html>    
                """);
        return html.toString();
    }

    public static String success() {
        String html = """
                <!DOCTYPE html>
                <html lang="pt-br">
                  <head>
                    <meta charset="UTF-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                    <link rel="preconnect" href="https://fonts.googleapis.com" />
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
                    <link
                      href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua:wght@400;700&display=swap"
                      rel="stylesheet"
                    />
                    <title>The New York Shows</title>
                  </head>
                  <style>
                    * {
                      margin: 0;
                      padding: 0;
                      font-family: "Inknut Antiqua", serif;
                      font-weight: 400;
                      font-size: 14px;
                    }
                                
                    header {
                      background-color: rgb(0, 2, 29);
                      color: white;
                      text-align: center;
                      font-size: 36px;
                      width: 100%;
                      padding: 10px 0;
                    }
                                
                    main {
                      background-color: rgb(0, 2, 29);
                      color: white;
                      width: fit-content;
                      margin: 30px auto;
                      border-radius: 50px;
                      text-align: center;
                      padding: 30px 50px;
                    }
                                
                    main h1 {
                      font-size: 36px;
                    }
                                
                    main h2 {
                      font-size: 18px;
                      margin: 0 10px;
                    }
                                
                    button {
                      width: 30%;
                      border-radius: 25px;
                      background-color: rgb(0, 85, 0);
                      margin-top: 15px;
                      color: white;
                      cursor: pointer;
                      border: none;
                    }
                                
                    button:hover {
                      background-color: rgb(0, 100, 0);
                    }
                                
                    button:first-child {
                      background-color: rgb(70, 70, 70);
                    }
                                
                    button:first-child:hover {
                      background-color: rgb(90, 90, 90);
                    }
                  </style>
                  <body>
                    <header>The New York Shows</header>
                    <main>
                      <h1>Sucesso!</h1>
                      <h2>Poltrona reservada com sucesso...</h2>
                      <a href="/"><button>Voltar</button></a>
                    </main>
                  </body>
                </html>  
                """;
        return html;
    }
    public static String fail() {
        String html = """
                <!DOCTYPE html>
                <html lang="pt-br">
                  <head>
                    <meta charset="UTF-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                    <link rel="preconnect" href="https://fonts.googleapis.com" />
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
                    <link
                      href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua:wght@400;700&display=swap"
                      rel="stylesheet"
                    />
                    <title>The New York Shows</title>
                  </head>
                  <style>
                    * {
                      margin: 0;
                      padding: 0;
                      font-family: "Inknut Antiqua", serif;
                      font-weight: 400;
                      font-size: 14px;
                    }
                                
                    header {
                      background-color: rgb(0, 2, 29);
                      color: white;
                      text-align: center;
                      font-size: 36px;
                      width: 100%;
                      padding: 10px 0;
                    }
                                
                    main {
                      background-color: rgb(0, 2, 29);
                      color: white;
                      width: fit-content;
                      margin: 30px auto;
                      border-radius: 50px;
                      text-align: center;
                      padding: 30px 50px;
                    }
                                
                    main h1 {
                      font-size: 36px;
                    }
                                
                    main h2 {
                      font-size: 18px;
                      margin: 0 10px;
                    }
                                
                    button {
                      width: 30%;
                      border-radius: 25px;
                      background-color: rgb(0, 85, 0);
                      margin-top: 15px;
                      color: white;
                      cursor: pointer;
                      border: none;
                    }
                                
                    button:hover {
                      background-color: rgb(0, 100, 0);
                    }
                                
                    button:first-child {
                      background-color: rgb(70, 70, 70);
                    }
                                
                    button:first-child:hover {
                      background-color: rgb(90, 90, 90);
                    }
                  </style>
                  <body>
                    <header>The New York Shows</header>
                    <main>
                      <h1>Ocorreu um erro!</h1>
                      <h2>A poltrona não pode ser reservada!</h2>
                      <a href="/"><button>Voltar</button></a>
                    </main>
                  </body>
                </html>
                """;
        return html;
    }

    public static String notFound() {
        String page = """
                <!DOCTYPE html>
                <html lang="pt-br">
                  <head>
                    <meta charset="UTF-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                    <link rel="preconnect" href="https://fonts.googleapis.com" />
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
                    <link
                      href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua:wght@400;700&display=swap"
                      rel="stylesheet"
                    />
                    <title>The New York Shows</title>
                  </head>
                  <style>
                    * {
                      margin: 0;
                      padding: 0;
                      font-family: "Inknut Antiqua", serif;
                      font-weight: 400;
                      font-size: 14px;
                    }
                                
                    header {
                      background-color: rgb(0, 2, 29);
                      color: white;
                      text-align: center;
                      font-size: 36px;
                      width: 100%;
                      padding: 10px 0;
                    }
                                
                    main {
                      background-color: rgb(0, 2, 29);
                      color: white;
                      width: fit-content;
                      margin: 30px auto;
                      border-radius: 50px;
                      text-align: center;
                      padding: 30px 50px;
                    }
                                
                    main h1 {
                      font-size: 36px;
                    }
                                
                    main h2 {
                      font-size: 18px;
                      margin: 0 10px;
                    }
                                
                    button {
                      width: 30%;
                      border-radius: 25px;
                      background-color: rgb(0, 85, 0);
                      margin-top: 15px;
                      color: white;
                      cursor: pointer;
                      border: none;
                    }
                                
                    button:hover {
                      background-color: rgb(0, 100, 0);
                    }
                                
                    button:first-child {
                      background-color: rgb(70, 70, 70);
                    }
                                
                    button:first-child:hover {
                      background-color: rgb(90, 90, 90);
                    }
                  </style>
                  <body>
                    <header>The New York Shows</header>
                    <main>
                      <h1>404 - Not Found</h1>
                      <h2>Página não encontrada!</h2>
                      <a href="/"><button>Voltar</button></a>
                    </main>
                  </body>
                </html>    
                """;
        return page;
    }
}
