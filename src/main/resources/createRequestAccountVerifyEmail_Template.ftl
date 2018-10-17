<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>MyTectWeb Demande de création de compte - Vérification email</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

   <img src="cid:MTW_Banner_wide.PNG"  style="display: block;" /><p></p>
   <p>Bonjour ${firstName},</p>
   <br>
   <p>Vous avez demandé la création d'un compte sur MyTecWeb pour l'UID ${uid}.</p>
   <p>Afin de confirmer votre demande, veuillez cliquer sur le lien suivant : <a href="${confirmLink}">Confirmer ma demande</a>
   <p></p>
   <p>Cordialement,</p>
   <p>L'équipe Administration de MyTecWeb.</p>
 
</body>
</html>