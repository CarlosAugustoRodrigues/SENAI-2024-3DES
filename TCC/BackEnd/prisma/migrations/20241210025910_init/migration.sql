-- DropForeignKey
ALTER TABLE `mensagem` DROP FOREIGN KEY `Mensagem_ocorrenciaId_fkey`;

-- AddForeignKey
ALTER TABLE `Mensagem` ADD CONSTRAINT `Mensagem_ocorrenciaId_fkey` FOREIGN KEY (`ocorrenciaId`) REFERENCES `Ocorrencia`(`id`) ON DELETE CASCADE ON UPDATE CASCADE;
