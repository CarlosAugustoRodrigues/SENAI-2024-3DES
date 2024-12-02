const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();

const registrarMensagem = async (req, res) => {
    try {
        const { mensagem, perfil, responsavel } = req.body;

        const novaMensagem = await prisma.mensagem.create({
            data : {
                mensagem: mensagem,
                ocorrenciaId: Number(req.params.ocorrenciaId),
                perfil: perfil.toUpperCase(),
                responsavel: responsavel
            }
        });

        res.status(201).json({ 
            message: "Mensagem enviada com sucesso!",
            mensagem: novaMensagem
        });
    } catch(error) {
        console.error(error)
        res.status(400).json({ message: "Erro ao enviar mensagem!" });
    }
}

const lerMensagens = async (req, res) => {
    try {
        const mensagens = await prisma.mensagem.findMany({
            where: { ocorrenciaId: Number(req.params.ocorrenciaId) }
        });

        res.status(200).json({ 
            message: "Mensagens lidas com sucesso!",
            mensgens: mensagens 
        });

    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao buscar mensagens!" });
    }
}

module.exports = {
    registrarMensagem,
    lerMensagens
}