const jwt = require('jsonwebtoken');
require('dotenv').config();

const validarAcessoFuncionario = (req, res, next) => {
    const token = req.headers.authorization;

    if(!token) {
        return res.status(403).json({ message: "Token de autenticação ausente!" });
    }

    const tokenString = token.replace("Bearer ", "");

    jwt.verify(tokenString, process.env.SECRET_KEY, (err, data) => {
        if(err) return res.status(401).json({ message: "Token inválido ou expirado!" });

        if(data.role !== 'FUNCIONARIO') {
        return res.status(403).json({ message: "Acesso negado!" });
        }

        next();
    });
}

const validarAcessoAdmin = (req, res, next) => {
    const token = req.headers.authorization;

    if(!token) {
        return res.status(403).json({ message: "Token de autenticação ausente!" });
    }

    const tokenString = token.replace("Bearer ", "");

    jwt.verify(tokenString, process.env.SECRET_KEY, (err, data) => {
        if(err) return res.status(401).json({ message: "Token inválido ou expirado!" });

        if(data.role !== 'ADMIN') {
        return res.status(403).json({ message: "Acesso negado!" });
        }

        next();
    });
}

const validarAcessoUsuario = (req, res, next) => {
    const token = req.headers.authorization;

    if(!token) {
        return res.status(403).json({ message: "Token de autenticação ausente!" });
    }

    const tokenString = token.replace("Bearer ", "");

    jwt.verify(tokenString, process.env.SECRET_KEY, (err, data) => {
        if(err) return res.status(401).json({ message: "Token inválido ou expirado!" });

        if(data.role !== 'USUARIO') {
        return res.status(403).json({ message: "Acesso negado!" });
        }

        next();
    });
}

module.exports = {
    validarAcessoAdmin,
    validarAcessoFuncionario,
    validarAcessoUsuario
}