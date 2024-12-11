export function InformacoesContato() {
  return (
    <div className="bg-white border border-input rounded-3xl px-8 py-6 space-y-6">
      <h2 className="text-xl font-medium">Informações de contato</h2>

      <div className="flex items-start gap-6">
        <div className="flex flex-col flex-1">
          <span className="font-medium text-sm">Nome Completo</span>
          <span>Davi Luan Manuel da Cruz</span>
        </div>

        <div className="flex flex-col flex-1">
          <span className="font-medium text-sm">Telefone</span>
          <span>(24) 2758-1193</span>
        </div>

        <div className="flex flex-col flex-1">
          <span className="font-medium text-sm">E-mail</span>
          <span>daviluandacruz@zf-lensysteme.com</span>
        </div>

        <div className="flex flex-col flex-1">
          <span className="font-medium text-sm">CNPJ</span>
          <span>119.220.336-22</span>
        </div>

        <div className="flex flex-col flex-1">
          <span className="font-medium text-sm">CPF</span>
          <span>119.220.336-22</span>
        </div>
      </div>
    </div>
  )
}
