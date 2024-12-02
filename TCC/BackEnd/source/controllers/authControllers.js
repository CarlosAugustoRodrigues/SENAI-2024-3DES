const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
require('dotenv').config();

const loginUsuario = async (req, res) => {
    try {
        const { email, password } = req.body;

        const contaUsuario = await prisma.contaUsuario.findUnique({
            where: { email: email }
        });

        if(!contaUsuario) {
            return res.status(400).json({ message: "Erro, usuario não encontrado!" });
        }

        const validarSenha = await bcrypt.compare(password, contaUsuario.password)
        if (!validarSenha) {
            return res.status(400).json({ message: "Erro, senha incorreta!" });
        }

        const usuario = await prisma.usuario.findUnique({
            where: { id: contaUsuario.usuarioId }
        });

        const token = await jwt.sign({ email: contaUsuario.email, usuarioId: usuario.id, role: contaUsuario.role }, 
            process.env.SECRET_KEY, {
            expiresIn: '1h'
        });

        res.status(200).json({
            message: "Autenticação feita com sucesso!",
            infoConta: {
                id: contaUsuario.id,
                email: contaUsuario.email,
                role: contaUsuario.role
            },
            Usuario: {
                id: usuario.id,
                imagemPerfil: usuario.imagemPerfil,
                nome: usuario.nome,
                telefone: usuario.telefone
            },
            token: token
        });
    } catch(error) {
        console.error(error);
        res.status(500).json({ message: "Erro ao autenticar usuario!" });
    }
}

const loginFuncionario = async (req, res) => {
    try {
        const { email, password } = req.body;

        const contaFuncionario = await prisma.contaFuncionario.findUnique({
            where: { email: email }
        });

        if(!contaFuncionario) {
            return res.status(400).json({ message: "Erro, funcionario não encontrado!" });
        }

        const validarSenha = await bcrypt.compare(password, contaFuncionario.password)
        if (!validarSenha) {
            return res.status(400).json({ message: "Erro, senha incorreta!" });
        }

        const funcionario = await prisma.funcionario.findUnique({
            where: { id: contaFuncionario.funcionarioId }
        });

        const token = await jwt.sign({ email: contaFuncionario.email, funcionarioId: funcionario.id, role: contaFuncionario.role }, 
            process.env.SECRET_KEY, {
            expiresIn: '1h'
        });

        res.status(200).json({
            message: "Autenticação feita com sucesso!",
            infoConta: {
                id: contaFuncionario.id,
                email: contaFuncionario.email,
                role: contaFuncionario.role
            },
            funcionario: {
                id: funcionario.id,
                imagemPerfil: funcionario.imagemPerfil,
                nome: funcionario.nome,
                setor: funcionario.setor
            },
            token: token
        });
    } catch(error) {
        console.error(error);
        res.status(500).json({ message: "Erro ao autenticar funcionario!" });
    }
}

module.exports = {
    loginUsuario,
    loginFuncionario
}