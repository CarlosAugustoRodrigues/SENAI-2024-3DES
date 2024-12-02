const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();

const registrarComentario = async (req, res) => {
    try {

        const { comentario, ocorrenciaId, usuarioId } = req.body;

        const novoComentario = await prisma.comentario.create({
            data : {
                comentario: comentario,
                ocorrenciaId: ocorrenciaId,
                usuarioId: usuarioId
            }
        });

        res.status(201).json({ 
            message: "Comentario feito com sucesso!",
            comentario: novoComentario
        });

    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao fazer comentario!" });
    }
}

const lerComentarios = async (req, res) => {
    try {
        const comentarios = await prisma.comentario.findMany({
            where: { ocorrenciaId: Number(req.params.ocorrenciaId) }
        });

        res.status(200).json({ 
            message: "Comentarios carregados com sucesso",
            comentarios: comentarios
        });
    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao buscar comentarios!" });
    }
}

module.exports = {
    registrarComentario,
    lerComentarios
}