'use client'

import { zodResolver } from "@hookform/resolvers/zod";
import { SubmitHandler, useForm } from "react-hook-form";
import { useFormState } from "@/contexts/Imovel/FormContext";
import { dadosEmpresaSchema, dadosEmpresaData } from "@/validation/imovel/representante";
import { Form } from "@/components/ui/form";
import { Button } from "@/components/ui/button";
import { DadosEmpresa } from "./dados-empresa";
import { CadastroRepresentantes } from "./step-representantes";

export function CadastroRepresentantePJ() {
  const { onHandleNext, setFormData, formData } = useFormState()

  const form = useForm<dadosEmpresaData>({
    resolver: zodResolver(dadosEmpresaSchema),
    defaultValues: formData
  })

  const onSubmit: SubmitHandler<dadosEmpresaData> = (data) => {
    setFormData((prev: any) => ({ ...prev, ...data }));
    onHandleNext()
  }

  return (
    <Form {...form}>
      <form
        onSubmit={form.handleSubmit(onSubmit)}
        className="space-y-6"
      >
        <div className="space-y-6">
          <DadosEmpresa />
          <CadastroRepresentantes />
        </div>

        <div className="flex justify-end mt-6">
          <Button type="submit">Avançar</Button>
        </div>
      </form>
    </Form>
  )
}
