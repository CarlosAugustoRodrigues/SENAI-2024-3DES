const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();
const bcrypt = require('bcryptjs');

const lerFuncionarios = async (req, res) => {
    try {
        const funcionarios = await prisma.funcionario.findMany({
            include: true
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
                responsabilidade: responsabilidade.toUpperCase()
            }
        });

        const contaFuncionario = await prisma.contaFuncionario.create({
            data: {
                email: email,
                password: await bcrypt.hash(password, 10),
                funcionarioId: novoFuncionario.id
            }
        });

        res.status(201).json({
            message: "Funcionário registrado com sucesso!",
            funcionario: novoFuncionario,
            contaFuncionario: contaFuncionario
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

module.exports = {
    lerFuncionarios,
    registrarFuncionario,
    alterarSenha
}