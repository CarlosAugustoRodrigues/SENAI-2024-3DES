const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();

const registrarOcorrencia = async (req, res) => {
    try {
        const { descricao, rua, bairro, cidade, cep, setor, usuario } = req.body;

        if (!descricao.trim() || !rua.trim() || !bairro.trim() || !cidade.trim() || !cep.trim() || !usuario) {
            return res.status(400).json({
                message: "Todos os campos obrigatórios devem ser preenchidos, exceto o setor!"
            });
        }

        const cepRegex = /^[0-9]{5}-?[0-9]{3}$/;
        if (!cepRegex.test(cep)) {
            return res.status(400).json({ message: "CEP inválido!" });
        }

        const usuarioExistente = await prisma.usuario.findUnique({
            where: { id: Number(usuario) },
        });

        if (!usuarioExistente) {
            return res.status(404).json({ message: "Usuário não encontrado!" });
        }

        let setorValidado = null;
        if (setor) {
            const setoresValidos = ["EDUCACAO", "VIAS", "INFRAESTRUTURA", "LAZER", "URBANISMO", "ESPORTE", "SAUDE"];
            if (!setoresValidos.includes(setor.toUpperCase().trim())) {
                return res.status(400).json({ message: "Setor inválido!" });
            }
            setorValidado = setor.toUpperCase().trim();
        }

        const novaOcorrencia = await prisma.ocorrencia.create({
            data: {
                descricao: descricao.trim(),
                rua: rua.trim(),
                bairro: bairro.trim(),
                cidade: cidade.trim(),
                cep: cep.trim(),
                setor: setorValidado,
                usuario: { connect: { id: Number(usuario) } }
            }
        });

        res.status(201).json({
            message: "Ocorrência registrada com sucesso!",
            ocorrencia: novaOcorrencia
        });

    } catch (error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao criar ocorrência!" });
    }
};

const lerOcorrenciaPorUsuario = async (req, res) => {
    try {
        const ocorrencias = await prisma.ocorrencia.findMany({
            where: { usuarioId: Number(req.params.usuarioId), ativo: "SIM" }
        });

        res.status(200).json({
            message: "Ocorrencias carregadas com sucesso!",
            ocorrencias: ocorrencias 
        });

    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao buscar ocorrencias!" });
    }
}

const lerOcorrencias = async (req, res) => {
    try {
        const ocorrencias = await prisma.ocorrencia.findMany({
            where: {
                ativo: "SIM", 
                status: {
                    in: ["APROVADA", "EM_DESENVOLVIMENTO"]
                }
            },
            include: {
                imagens: true,
            }
        });

        res.status(200).json({
            message: "Ocorrencias carregadas com sucesso!",
            ocorrencias: ocorrencias 
        });

    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao buscar ocorrencias!" });
    }
}

const lerUsuarioPorOcorrencia = async (req, res) => {
    try {
        const ocorrencia = await prisma.ocorrencia.findUnique({
            where: { id: Number(req.params.ocorrenciaId) }
        });

        const usuario = await prisma.usuario.findUnique({
            where: { id: ocorrencia.usuarioId }
        });

        res.status(200).json({
            message: "Usuario carregado com sucesso!",
            usuario: usuario 
        });
    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao buscar usuario!" });
    }
}

const lerOcorrenciasEncerradas = async (req, res) => {
    try {
        const ocorrencias = prisma.ocorrencia.findMany({
            where: {ativo: "SIM", status: "ENCERRADA"}
        });

        res.status(200).json({
            message: "Ocorrencias carregadas com sucesso!",
            ocorrencias: ocorrencias 
        });

    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao buscar ocorrencias!" });
    }
}

const excluirOcorrencia = async (req, res) => {
    try {
        const ocorrencia = await prisma.ocorrencia.findUnique({
            where: { id: Number(req.params.ocorrenciaId) }
        });

        if(ocorrencia.usuarioId != Number(req.params.usuarioId)) {
            return res.status(400).json({ 
                message: "Erro, não é possível excluir ocorrencias de outros usuarios!" 
            });
        }

        await prisma.ocorrencia.update({
            where: { id: Number(req.params.ocorrenciaId) },
            data: {
                ativo: "NAO"
            }
        })

        res.status(200).json({ message: "Ocorrencia excluída com sucesso!" });

    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao excluir ocorrencias!" });
    }
}


const lerOcorrenciaPorFuncionario = async (req, res) => {
    try {
        const funcionario = await prisma.funcionario.findUnique({
            where: { id: Number(req.params.funcionarioId) }
        });

        const ocorrencias = await prisma.ocorrencia.findMany({
            where: { 
                funcionarioId: funcionario.id, 
                ativo: "SIM",
            },
        });

        res.status(200).json({
            message: "Ocorrencias carregadas com sucesso!",
            ocorrencias: ocorrencias 
        });

    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao buscar ocorrencias!" });
    }
}

const lerOcorrenciaPorSetorIntermediador = async (req, res) => {
    try {
        const funcionario = await prisma.funcionario.findUnique({
            where: { id: Number(req.params.funcionarioId) }
        });

        if(funcionario.responsabilidade != "INTERMEDIAR") {
            return res.status(400).json({ message: "Erro ao buscar ocorrencias! Não permitido." });
        }

        const ocorrencias = await prisma.ocorrencia.findMany({
            where: { 
                setor: req.params.setor,
                status: 'ENVIADA_AO_SETOR',
                ativo: "SIM" 
            }
        });

        res.status(200).json({
            message: "Ocorrencias carregadas com sucesso!",
            ocorrencias: ocorrencias
        });

    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao buscar ocorrencias!" });
    }
}

const atribuirOcorrenciaFuncionarioIntermediador = async (req, res) => {
    try {
        const funcionario = await prisma.funcionario.findUnique({
            where: { id: Number(req.params.funcionarioId) }
        });

        const ocorrencia = await prisma.ocorrencia.findUnique({
            where: { id: Number(req.params.ocorrenciaId) }
        });

        if (!funcionario) {
            return res.status(400).json({ message: "Erro ao encontrar funcionario!" });
        }

        if(funcionario.responsabilidade != "INTERMEDIAR") {
            return res.status(400).json({ message: "Erro, ação não permitida!" });
        }

        if(!ocorrencia) {
            return res.status(400).json({ message: "Erro ao encontrar ocorrencia!" });
        }

        await prisma.ocorrencia.update({
            where: { id: Number(req.params.ocorrenciaId) },
            data: {
                funcionarioId: funcionario.id
            }
        })

    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao atribuir ocorrencia!" });
    }
}

const alterarStatusOcorrenciaIntermediador = async (req, res) => {
    try {
        const {status} = req.body;

        const funcionario = await prisma.funcionario.findUnique({
            where: { id: Number(req.params.funcionarioId) }
        });

        if(!funcionario) {
            return res.status(400).json({ message: "Erro, funcionario não encontrado!" });
        }

        const ocorrencia = await prisma.ocorrencia.findUnique({
            where: { id: Number(req.params.ocorrenciaId) }
        });

        if(!ocorrencia) {
            return res.status(400).json({ message: "Erro, ocorrencia não encontrada!" });
        }

        if(ocorrencia.funcionarioId != Number(req.params.funcionarioId) || funcionario.responsabilidade != "INTERMEDIAR") {
            return res.status(400).json({ message: "Erro, ação não permitida!" });
        }

        await prisma.ocorrencia.update({
            where: { id: ocorrencia.id },
            data: {
                status: status.toUpperCase()
            }
        });

        res.status(200).json({ message: "Status alterado com sucesso!" });
    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao alterar status da ocorrencia!" });
    }
}

const lerOcorrenciaRegistradaSetorAnalista = async (req, res) => {
    try {
        const funcionario = await prisma.funcionario.findUnique({
            where : { id: Number(req.params.funcionarioId) }
        });

        if(!funcionario) {
            return res.status(400).json({ message: "Erro, funcionario não encontrado!" });
        }

        if(funcionario.responsabilidade != "ANALISAR") {
            return res.status(400).json({ message: "Erro, ação não permitida!" });
        }

        const ocorrencias = await prisma.ocorrencia.findMany({
            where: {
                setor: funcionario.setor,
                status: "REGISTRADA",
                ativo: "SIM"
            }
        });

        res.status(200).json({
            message: "Ocorrencias carregadas com sucesso",
            ocorrencias: ocorrencias
        });
    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao buscar ocorrencia!" });
    }
}

const alterarInfoOcorrenciaAnalista = async (req, res) => {
    try {
        const { descricao, rua, bairro, cidade, cep, setor } = req.body;

        const funcionario = await prisma.funcionario.findUnique({
            where: { id: Number(req.params.funcionarioId) }
        });

        if(!funcionario) {
            return res.status(400).json({ message: "Erro, funcionario não encontrado!" });
        }

        if(funcionario.responsabilidade != "ANALISAR") {
            return res.status(400).json({ message: "Erro, ação não permitida!" });
        }

        const ocorrencia = await prisma.ocorrencia.findUnique({
            where: { id: Number(req.params.ocorrenciaId) }
        });

        if(!ocorrencia) {
            return res.status(400).json({ message: "Erro, ocorrencia não encontrada!" });
        }

        const ocorrenciaAlterada = await prisma.ocorrencia.update({
            where: { id: ocorrencia.id },
            data: {
                descricao: descricao,
                rua: rua,
                bairro: bairro,
                cidade: cidade,
                cep: cep,
                setor: setor
            }
        });

        res.status(200).json({
            message: "Informações da ocorrencia alterada com sucesso!",
            ocorrencia: ocorrenciaAlterada
        });
    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao tentar alterar informações da ocorrencia!" })
    }
}

const enviarOcorrenciaIntermediador = async (req, res) => {
    try {
        const funcionario = await prisma.funcionario.findUnique({
            where: { id: Number(req.params.funcionarioId) }
        });

        if(!funcionario) {
            return res.status(400).json({ message: "Erro, funcionario não encontrado!" });
        }

        if(funcionario.responsabilidade != "ANALISAR") {
            return res.status(400).json({ message: "Erro, ação não permitida!" });
        }

        const ocorrencia = await prisma.ocorrencia.findUnique({
            where: { id: Number(req.params.ocorrenciaId) }
        });

        if(!ocorrencia) {
            return res.status(400).json({ message: "Erro, ocorrencia não encontrada!" });
        }

        const ocorrenciaEnviada = await prisma.ocorrencia.update({
            where: { id: ocorrencia.id },
            data: {
                status: "ENVIADA_AO_SETOR"
            }
        });

        res.status(200).json({
            message: "Ocorrencia enviada para setor responsavel!",
            ocorrencia: ocorrenciaEnviada
        });
    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao enviar ocorrencia para funcionario intermediador!" })
    }
}

const rejeitarOcorrenciaAnalista = async (req, res) => {
    try {
        const funcionario = await prisma.funcionario.findUnique({
            where: { id: Number(req.params.funcionarioId) }
        });

        if(!funcionario) {
            return res.status(400).json({ message: "Erro, funcionario não encontrado!" });
        }

        if(funcionario.responsabilidade != "ANALISAR") {
            return res.status(400).json({ message: "Erro, ação não permitida!" });
        }

        const ocorrencia = await prisma.ocorrencia.findUnique({
            where: { id: Number(req.params.ocorrenciaId) }
        });

        if(!ocorrencia) {
            return res.status(400).json({ message: "Erro, ocorrencia não encontrada!" });
        }

        await prisma.ocorrencia.delete({
            where: { id: ocorrencia.id }
        });

        res.status(200).json({
            message: "Ocorrencia excluida!",
        });
    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao excluir ocorrencia!" })
    }
}

module.exports = {
    registrarOcorrencia,
    lerOcorrenciaPorUsuario,
    lerOcorrencias,
    lerUsuarioPorOcorrencia,
    lerOcorrenciasEncerradas,
    excluirOcorrencia,

    lerOcorrenciaPorFuncionario,
    lerOcorrenciaPorSetorIntermediador,
    atribuirOcorrenciaFuncionarioIntermediador,
    alterarStatusOcorrenciaIntermediador,

    lerOcorrenciaRegistradaSetorAnalista,
    alterarInfoOcorrenciaAnalista,
    enviarOcorrenciaIntermediador,
    rejeitarOcorrenciaAnalista
}