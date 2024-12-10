const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();
const bcrypt = require('bcryptjs');

const lerFuncionarios = async (req, res) => {
    try {
        const funcionarios = await prisma.funcionario.findMany({
            where: {
                infoConta: {
                    some: {
                        role: 'FUNCIONARIO',
                    }
                }
            },
            include: {
                infoConta: true
            }
        });

        res.status(200).json({
            message: "Funcionarios carregados com sucesso!",
            funcionarios: funcionarios
        });
        
    } catch(error) {
        console.error(error);
        res.status(400).json({ message: "Erro ao buscar funcionarios!" });
    }
}

const registrarFuncionario = async (req, res) => {
    try {
        const { nome, setor, responsabilidade, email, password } = req.body;

        const emailExistente = await prisma.contaFuncionario.findUnique({
            where: {email: email}
        });

        if (emailExistente) {
            return res.status(400).json({ message: "Email já registrado para funcionario!" });
        }

        const novoFuncionario = await prisma.funcionario.create({
            data: {
                nome: nome,
                setor: setor.toUpperCase(),
                responsabilidade: responsabilidade.toUpperCase(),
                infoConta: {
                    create: {
                        email: email,
                        password: await bcrypt.hash(password, 10)
                    }
                }
            }
        });

        res.status(201).json({
            message: "Funcionário registrado com sucesso!",
            funcionario: novoFuncionario,
        });

    } catch(error) {
        console.log(error);
        res.status(400).json({ message: "Erro ao registrar funcionario!" });
    }
}

const alterarSenha = async (req, res) => {
    const { password } = req.body;

    if(!password) {
        return res.status(404).json({ message: "Senha não fornecida!" });
    }

    try {
        const funcionario = await prisma.contaFuncionario.findUnique({
            where: {email: req.params.email}
        });

        if(!funcionario) {
            return res.status(404).json({ message: "Funcionario não encontrado!" });
        }

        await prisma.contaFuncionario.update({
            where: {email: req.params.email},
            data: {
                password: await bcrypt.hash(password, 10)
            }
        });

        res.status(200).json({
            message: "Senha alterada com sucesso!"
        });

    } catch(error) {
        console.error(error);
        res.status(400).json({message: "Erro ao alterar senha do funcionario!"});
    }
}

const imagemPerfil = async (req, res) => {
    try {
        const imagem = req.files.map(file => ({ imagemPerfil: file.buffer }));

        const funcionario = await prisma.funcionario.update({
        where: { id: Number(req.params.funcionarioId) },
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
    lerFuncionarios,
    registrarFuncionario,
    alterarSenha,
    imagemPerfil
}