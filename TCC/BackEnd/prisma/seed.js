const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();
const bcrypt = require('bcryptjs');

async function main() {

    await prisma.funcionario.create({
        data: {
            nome: "ADMIN",
            infoConta: {
                create: {
                    email: "sisur@gmail.com",
                    password: await bcrypt.hash("admin", 10),
                    role: "ADMIN"
                }
            }
        }
    });

    console.log('Banco de dados semeado com sucesso!');
}

main()
  .catch(e => {
    console.error(e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
