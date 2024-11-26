const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();

const readAll = async (req, res) => {
    try {
        const lancamentos = await prisma.tb_lancamentos.findMany();
        
        res.status(200).json(lancamentos).end();
    } catch (error) {
        res.status(400).json("Erro ao buscar lançamentos").end();
    }
}

const readById = async (req, res) => {
    try {
        const lancamento = await prisma.tb_lancamentos.findUnique({
            where: {
                id: Number(req.params.id)
            }
        });

    } catch (error) {
        res.status(400).json("Lançamento não encontrado").end();
    }
}

const create = async (req, res) => {
    const { descricao, valor, tipo, emailUsuario } = req.body;

    try {
        const usuarioId = await prisma.tb_usuarios.findUnique({
            where: { email: emailUsuario }
        });

        const novoLancamento = await prisma.tb_lancamentos.create({
            descricao: descricao,
            valor: valor,
            usuario_id: usuarioId.id,
            tipo: tipo
        })

        res.status(201).json(novoLancamento).end();
    } catch (error) {
        res.status(400).json("Não foi possível criar um novo lançamento").end();
    }
}

const update = async (req, res) => {
    const { descricao, valor, tipo } = req.body;

    try {
        const updateLancamento = await prisma.tb_lancamentos.update({
            where: {
                id: Number(req.params.id)
            },
            data: {
                descricao: descricao,
                valor: valor,
                tipo: tipo
            }
        });

        res.status(200).json(updateLancamento).end();
    } catch (error) {
        res.status(400).json("Lançamento não encontrado ou impossível fazer alterações");
    }
}

const del = async (req, res) => {
    try {
        const lancamento = await prisma.tb_lancamentos.delete({
            where: { 
                id: Number(req.params.id)
            }
        })
    } catch (error) {
        res.status(400).json("Lançamento não encontrado").end();
    }
}

module.exports = {
    readAll,
    readById,
    create,
    update,
    del
}