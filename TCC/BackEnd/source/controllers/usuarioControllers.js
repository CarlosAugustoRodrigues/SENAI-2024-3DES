const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();
const bcrypt = require('bcryptjs');

const registrarUsuario = async (req, res) => {
    try {
        const { nome, telefone, email, password } = req.body;

        const emailExistente = await prisma.contaUsuario.findUnique({
            where: {email}
        });

        if (emailExistente) {
            return res.status(400).json({message: "Email já registrado!"});
        }

        const novoUsuario = await prisma.usuario.create({
            data: {
                nome: nome, 
                telefone: telefone,
                infoConta: {
                    create: {
                        email: email,
                        password: await bcrypt.hash(password, 10),
                    }
                }
            }
        });

        res.status(201).json({
            message: "Usuario registrado com sucesso!",
            Usuario: novoUsuario
        });

    } catch (error) {
        console.error(error);
        res.status(400).json({message: "Erro ao registrar usuario!"});
    }
}

const lerUsuarios = async (req, res) => {
    try {
        const usuarios = await prisma.usuario.findMany({
            include: {
                infoConta: true,
                ocorrencia: true,
                comentario: true
            }
        });

        res.status(200).json({
            message: "Usuarios carregados com sucesso!",
            usuarios: usuarios
        });
        
    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao buscar usuarios!" });
    }
}

const alterarSenha = async (req, res) => {
    const { password } = req.body;

    if(!password) {
        return res.status(404).json({ message: "Senha não fornecida!" });
    }

    try {
        const usuario = await prisma.contaUsuario.findUnique({
            where: {email: req.params.email} 
        });

        if (!usuario) {
            return res.status(404).json({ message: "Usuario não encontrado!" });
        }

        await prisma.contaUsuario.update({
            where: {email: req.params.email},
            data: {
                password: await bcrypt.hash(password, 10)
            }
        });

        res.status(200).json({
            message: "Senha alterada com sucesso!"
        });

    } catch (error) {
        console.error(error);
        res.status(400).json({message: "Erro ao alterar senha do usuario!"});
    }
}

const imagemPerfil = async (req, res) => {
    try {
        const imagem = req.files.map(file => ({ imagemPerfil: file.buffer }));

        const usuario = await prisma.usuario.update({
        where: { id: Number(req.params.usuarioId) },
        data: {
            imagemPerfil: imagem
        }
    });

    res.status(200).json({
        message: "Imagem de perfil alterada com sucesso!"
    });

    } catch(error) {
        console.error(error);
        res.status(400).json({message: "Erro ao alterar imagem de perfil!"});
    }
}

module.exports = {
    registrarUsuario,
    alterarSenha,
    lerUsuarios,
    imagemPerfil
}