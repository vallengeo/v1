import { z } from "zod"
import { estados } from "@/validation/estados"

export const grupos = [
  'residencial',
  'comercial',
  'misto',
  'industrial',
] as const

export type Grupos = typeof grupos[number]

export const mapearGrupos: { [key in Grupos]: string } = {
  residencial: "Residencial",
  comercial: "Comercial",
  misto: "Misto",
  industrial: "Industrial",
}

export const imovelFormSchema = z.object({
  grupo: z.enum(grupos, {
      errorMap: () => ({ message: "Tipo do grupo é obrigatório" })
    }),
  cep: z
    .string({ required_error: "CEP é obrigatório" }),
  endereco: z
    .string({ required_error: "Endereço é obrigatório" }),
  numero: z
    .string({ required_error: "Número é obrigatório" }),
  complemento: z
    .string().optional(),
  bairro: z
    .string({ required_error: "Bairro é obrigatório" }),
  cidade: z
    .string({ required_error: "Cidade é obrigatório" }),
  uf: z
    .enum(estados, {
      errorMap: () => ({ message: "Estado é obrigatório" })
    }),
  setor: z
    .string({ required_error: "Setor é obrigatório" }),
  quadra: z
    .string({ required_error: "Quadra é obrigatório" }),
  lote: z
    .string({ required_error: "Lote é obrigatório" }),
  unidade: z
    .string().optional(),
  area_terreno: z
    .string({ required_error: "Área do terreno é obrigatório" }),
  testada: z
    .string({ required_error: "Testada é obrigatório" }),
  fracao: z
    .string().optional(),
  data_inclusao: z.date({ required_error: "Data é obrigatório" }),
  observacao: z.string().optional(),
})

export type imovelFormData = z.infer<typeof imovelFormSchema>
