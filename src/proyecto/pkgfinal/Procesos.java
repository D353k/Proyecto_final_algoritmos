/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal;

import java.util.Scanner;

/**
 *
 * @author Derek
 */
public class Procesos {

    public static void Sistema_Ventas() {
        //Crear variable que escanea consola para asignar datos a variables
        Scanner sc = new Scanner(System.in);

        //Variables del menu y salida
        int opcion;
        int confirmar;
        boolean salir = false;

        //Datos de cada producto
        int codigo;
        int cdproducto;
        float precio;
        float subtotalActual = 0;
        String nombre;

        //Datos de la venta
        int cantidad;
        int unidadesVenta = 0;
        float subtotalFinal = 0;
        double descuento = 0;
        double contardescuento = 0;
        double IVA;
        double total;
        String detalleCompra = "";

        //Variables para el cobro
        int mp;
        int aprobacion = 0;
        int pagadocon = 0;
        double dineroRecibido = 0;
        double abonoRestante = 0;
        double dineroEntregado = 0;
        boolean pago = false;
        String pagastring = null;

        //Contadores para caja y estadisticas
        int numeroVentas = 0;
        int totalProductosVendidos = 0;
        int pagosEFectivo = 0;
        int pagosTArjeta = 0;
        int pagosTRansferencia = 0;
        double totalAbsoluto = 0;
        double ventaMayor = 0;
        double ventaMenor = 0;
        double promedioVenta;
        boolean primeraVenta = true;
        //ciclo
        do {
            System.out.println();
            System.out.println("=========================================");
            System.out.println("          SISTEMA DE VENTAS    ");
            System.out.println("=========================================");
            System.out.println("Indique que accion desea realizar");
            System.out.println("1. Registrar una venta");
            System.out.println("2. Consultar caja");
            System.out.println("3. Mostrar Estadisticas");
            System.out.println("4. Salir");
            System.out.println("==========================================");

            opcion = sc.nextInt();

            //opciones
            switch (opcion) {
                //caso en donde registramos venta
                case 1:
                    System.out.println();
                    System.out.println("Procediendo a registrar venta...");
                    pago = false;
                    unidadesVenta = 0;
                    detalleCompra = "";
                    subtotalFinal = 0;
                    System.out.println("Cuantos productos desea comprar:");
                    cantidad = sc.nextInt();
                    while (cantidad <= 0) {
                        System.out.println("La cantidad ingresada no es valida, ingresela nuevamente:");
                        cantidad = sc.nextInt();
                    }

                    for (int i = 0; i < cantidad; i++) {
                        //Ciclo recibiendo cada producto por separado con sus datos
                        System.out.println("---------------------------------------");
                        System.out.println("--- Producto " + (i + 1) + " ---");
                        System.out.println("Ingrese el codigo del producto:");
                        codigo = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Ingrese el nombre del producto:");
                        nombre = sc.nextLine();

                        System.out.println("Ingrese el precio del producto:");
                        precio = sc.nextFloat();
                        while (precio <= 0) {
                            System.out.println("El precio ingresado no es valido, ingreselo nuevamente:");
                            precio = sc.nextFloat();
                        }

                        System.out.println("Ingrese la cantidad del producto a comprar");
                        cdproducto = sc.nextInt();
                        while (cdproducto <= 0) {
                            System.out.println("La cantidad ingresada no es valida, ingresela nuevamente:");
                            cdproducto = sc.nextInt();
                        }
                        
                        //Confirmacion de registro
                        System.out.println("Producto " + (i + 1) + " registrado");
                        System.out.println("---------------------------------------");
                        totalProductosVendidos = totalProductosVendidos + cdproducto;
                        
                        unidadesVenta = unidadesVenta + cdproducto;
                        subtotalActual = cdproducto * precio;
                        //acumuladores globales
                        subtotalFinal = subtotalFinal + subtotalActual;
                        detalleCompra = detalleCompra + "Producto " + (i + 1) + ": " + nombre
                                + ", Codigo: " + codigo
                                + ", Cantidad: " + cdproducto
                                + ", Precio unitario: Q" + precio
                                + ", Precio total: Q" + subtotalActual + "\n";
                    }
                    //zona descuentos
                    if (subtotalFinal >= 200) {
                        if (subtotalFinal >= 500) {
                            if (subtotalFinal >= 1000) {
                                descuento = subtotalFinal * 0.10;
                            } else {
                                descuento = subtotalFinal * 0.08;
                            }
                        } else {
                            descuento = subtotalFinal * 0.05;
                        }
                    } else {
                        descuento = 0;
                    }
                    //aplicamos descuento obtenido//
                    contardescuento = subtotalFinal - descuento;

                    IVA = contardescuento * 0.12;

                    total = contardescuento + IVA;
                    total = Math.round(total * 100) / 100.0;
                    while (pago == false) {
                        System.out.printf("El monto a pagar es de: Q%.2f%n", total);
                        System.out.println("Con que metodo de pago desea pagar? ");
                        System.out.println("1. Efectivo");
                        System.out.println("2. Tarjeta");
                        System.out.println("3. Transferencia");
                        mp = sc.nextInt();

                        switch (mp) {
                            case 1:
                                System.out.println("Cuanto dinero esta brindando el cliente?");
                                dineroRecibido = sc.nextDouble();
                                while (dineroRecibido <= 0) {
                                    System.out.println("La cantidad ingresada no es valida, ingresela nuevamente:");
                                    dineroRecibido = sc.nextDouble();
                                }
                                while (dineroRecibido < total) {
                                    System.out.printf("La cantidad de dinero recibida no es suficiente, aun quedan: Q%.2f%n", (total - dineroRecibido));
                                    abonoRestante = sc.nextDouble();
                                    while (abonoRestante <= 0) {
                                        System.out.println("El abono ingresado no es valido, ingreselo nuevamente:");
                                        abonoRestante = sc.nextDouble();
                                    }
                                    dineroRecibido = dineroRecibido + abonoRestante;
                                }
                                dineroEntregado = dineroRecibido - total;
                                System.out.printf("El vuelto a brindar seria: Q%.2f%n", dineroEntregado);
                                pago = true;
                                pagosEFectivo = pagosEFectivo + 1;
                                pagadocon = 1;
                                pagastring = "Efectivo";
                                break;
                            case 2:
                                System.out.println();
                                System.out.println("Inserte tarjeta en la pinpad");
                                System.out.println("...");
                                System.out.println("...");
                                System.out.println("...");
                                System.out.println("La transaccion fue aprobada?");
                                System.out.println("1. Si");
                                System.out.println("2. No");
                                aprobacion = sc.nextInt();
                                if (aprobacion == 1) {
                                    pago = true;
                                    pagosTArjeta = pagosTArjeta + 1;
                                    pagadocon = 2;
                                    pagastring = "Tarjeta";
                                } else {
                                    pago = false;
                                }
                                break;
                            case 3:
                                System.out.println();
                                System.out.println("La transferencia fue recibida?");
                                System.out.println("1. Si");
                                System.out.println("2. No");
                                aprobacion = sc.nextInt();
                                if (aprobacion == 1) {
                                    pago = true;
                                    pagosTRansferencia = pagosTRansferencia + 1;
                                    pagadocon = 3;
                                    pagastring = "Transferencia";
                                } else {
                                    pago = false;
                                }
                                break;
                            default:
                                System.out.println("Ingrese una opcion valida");
                                break;
                        }
                    }
                    numeroVentas = numeroVentas + 1;
                    System.out.println("*****************************************");
                    System.out.println("             RESUMEN DE VENTA");
                    System.out.println("*****************************************");
                    System.out.println("Productos distintos: " + cantidad);
                    System.out.println("Unidades compradas: " + unidadesVenta);
                    System.out.println("=========================================================");
                    System.out.println("Lo que se compro:");
                    System.out.print(detalleCompra);
                    System.out.println("=========================================================");
                    System.out.printf("El subtotal a pagar es de: Q%.2f%n", subtotalFinal);
                    System.out.printf("El descuento fue de: Q%.2f%n", descuento);
                    System.out.printf("Subtotal con descuento: Q%.2f%n", contardescuento);
                    System.out.printf("El IVA fue de: Q%.2f%n", IVA);
                    System.out.printf("El total a pagar fue de: Q%.2f%n", total);
                    System.out.println("La forma de pago fue: " + pagastring);
                    if (pagadocon == 1) {
                        System.out.printf("El pago recibido fue de: Q%.2f%n", dineroRecibido);
                        System.out.printf("El cambio entregado fue de: Q%.2f%n", dineroEntregado);
                    }
                    System.out.println("*****************************************");
                    totalAbsoluto = totalAbsoluto + total;
                    //contadores / almacenadores
                    if (primeraVenta) {
                        ventaMayor = total;
                        ventaMenor = total;
                        primeraVenta = false;
                    } else {
                        if (total > ventaMayor) {
                            ventaMayor = total;
                        }
                        if (total < ventaMenor) {
                            ventaMenor = total;
                        }
                    }
                    break;

                case 2:
                    System.out.println();
                    //datos solicitados
                    System.out.println("Consultando estado de caja...");
                    System.out.println("$$$$$$$$$$$$$$ ESTADO DE CAJA $$$$$$$$$$$$$$");
                    System.out.println("Ventas realizadas: " + numeroVentas);
                    System.out.println("Productos vendidos: " + totalProductosVendidos);
                    System.out.printf("Total vendido: Q%.2f%n", totalAbsoluto);
                    System.out.println("Ventas en efectivo: " + pagosEFectivo);
                    System.out.println("Ventas con tarjeta: " + pagosTArjeta);
                    System.out.println("Ventas por transferencia: " + pagosTRansferencia);
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.println(".");
                    break;

                case 3:
                    System.out.println();
                    System.out.println("Consultando estadisticas...");
                    if (numeroVentas == 0) {
                        System.out.println("No existen ventas registradas.");
                    } else {
                        //calculo de promedio
                        System.out.println();
                        promedioVenta = totalAbsoluto / numeroVentas;
                        System.out.println(":::::::::::::: ESTADISTICAS ::::::::::::::");
                        System.out.println("Numero total de ventas: " + numeroVentas);
                        System.out.println("Cantidad total de productos vendidos: " + totalProductosVendidos);
                        System.out.printf("Total de dinero vendido: Q%.2f%n", totalAbsoluto);
                        System.out.printf("Promedio de dinero por venta: Q%.2f%n", promedioVenta);
                        System.out.println("Ventas en efectivo: " + pagosEFectivo);
                        System.out.println("Ventas con tarjeta: " + pagosTArjeta);
                        System.out.println("Ventas por transferencia: " + pagosTRansferencia);
                        System.out.printf("Venta de mayor valor: Q%.2f%n", ventaMayor);
                        System.out.printf("Venta de menor valor: Q%.2f%n", ventaMenor);
                        System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println(".");
                    }
                    break;

                case 4:
                    System.out.println();
                    System.out.println("Seguro que desea salir del sistema?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    confirmar = sc.nextInt();
                    while (confirmar != 1 && confirmar != 2) {
                        System.out.println("Opcion no valida, ingrese 1 (Si) o 2 (No):");
                        confirmar = sc.nextInt();
                    }
                    if (confirmar == 1) {
                        salir = true;
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println(" Gracias por usar el sistema. Hasta pronto!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    } else {
                        System.out.println("Regresando al menu principal...");
                    }
                    break;
                default:
                    System.out.println("Ingrese una accion disponible");
                    break;

            }
        } while (salir == false);
    }
}
