const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();

const readAll = async (req, res) => {
    try {
        const usuarios = await prisma.tb_usuarios.findMany();

        res.status(200).json(usuarios).end();
    }
    catch (error) {
        res.status(400).json("Erro ao buscar usuarios").end();
    }
}

const readById = async (req, res) => {
    try {
        const usuario = await prisma.tb_usuarios.findUnique({
            where: { 
                id: Number(req.params.id)
            }
        })

        res.status(200).json(usuario).end();
    } catch (error) {
        res.status(400).json("Usuario não encontrado").end();
    }
}

const create = async (req, res) => {
    const { nome, email } = req.body;

    try {
        const novoUsuario = await prisma.tb_usuarios.create({
            data: {
                nome: nome,
                email: email
            }
        });

        res.status(201).json(novoUsuario).end();
    } catch (error) {
        res.status(400).json("Não foi possível registrar usuario");
    }
}

module.exports = {
    readAll,
    readById,
    create
}