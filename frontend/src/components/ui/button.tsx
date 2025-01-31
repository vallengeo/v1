import * as React from "react"
import { Slot } from "@radix-ui/react-slot"
import { cva, type VariantProps } from "class-variance-authority"

import { cn } from "@/lib/utils"

const buttonVariants = cva(
  "inline-flex items-center justify-center rounded-md text-sm font-medium transition-colors focus-visible:outline-none disabled:pointer-events-none disabled:border-[#DBDBDB] disabled:bg-[#DBDBDB] disabled:text-[#BABABA]",
  {
    variants: {
      variant: {
        default: "text-lg font-semibold bg-primary text-primary-foreground hover:bg-primary-hover hover:text-primary-foreground-hover hover:shadow-[0_4px_4px_0_rgba(0,0,0,0.10)]",
        secondary: "text-lg bg-transparent text-primary-foreground font-semibold hover:bg-primary-foreground hover:text-white border border-primary-foreground",
        tertiary: "text-lg text-white bg-primary-foreground font-medium hover:opacity-90 border border-primary-foreground",
        ghost: "hover:bg-accent hover:text-accent-foreground",
        "gov-br": "text-lg text-gov-br-foreground bg-gov-br hover:bg-gov-br-hover",
        outline: "border border-input bg-background hover:bg-accent hover:text-accent-foreground",
        link: "text-primary-foreground hover:text-primary-hover underline-offset-4 underline",
        "no-style": "font-normal",
      },
      size: {
        default: "h-10 rounded-3xl px-10 py-2",
        sm: "h-9 rounded-md px-3",
        lg: "h-11 rounded-md px-8",
        icon: "h-10 w-10",
        "no-style": "",
      },
    },
    defaultVariants: {
      variant: "default",
      size: "default",
    },
  }
)

export interface ButtonProps
  extends React.ButtonHTMLAttributes<HTMLButtonElement>,
    VariantProps<typeof buttonVariants> {
  asChild?: boolean
}

const Button = React.forwardRef<HTMLButtonElement, ButtonProps>(
  ({ className, variant, size, asChild = false, ...props }, ref) => {
    const Comp = asChild ? Slot : "button"
    return (
      <Comp
        className={cn(buttonVariants({ variant, size, className }))}
        ref={ref}
        {...props}
      />
    )
  }
)
Button.displayName = "Button"

export { Button, buttonVariants }
