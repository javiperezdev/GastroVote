package es.dam.gastrovote.javiperez.data

import es.dam.gastrovote.javiperez.domain.model.Bar
import es.dam.gastrovote.javiperez.domain.model.Tapa

class BarRepository {
    val bars: List<Bar> = listOf(
        Bar(
            id = 1,
            name = "La Taberna del Puerto",
            tapa = Tapa(
                id = 1,
                name = "Marinera murciana",
                description = "Rosquilla crujiente con ensaladilla rusa y una anchoa.",
                price = 3.50,
                rating = 4
            ),
            phone = "+34 968 21 45 67",
            address = "Calle Mayor, 12, 30001 Murcia",
            web = "https://latabernadelpuerto.es"
        ),
        Bar(
            id = 2,
            name = "El Rincón Murciano",
            tapa = Tapa(
                id = 2,
                name = "Zarangollo",
                description = "Revuelto tradicional de calabacín, cebolla y huevo.",
                price = 4.00,
                rating = 4
            ),
            phone = "+34 968 22 18 90",
            address = "Plaza de las Flores, 5, 30004 Murcia",
            web = "https://elrinconmurciano.es"
        ),
        Bar(
            id = 3,
            name = "Casa Carmela",
            tapa = Tapa(
                id = 3,
                name = "Croqueta de jamón ibérico",
                description = "Croqueta casera de bechamel cremosa con jamón ibérico.",
                price = 2.80,
                rating = 5
            ),
            phone = "+34 968 23 56 71",
            address = "Calle Jara Carrillo, 8, 30001 Murcia",
            web = "https://casacarmela.es"
        ),
        Bar(
            id = 4,
            name = "La Esquina del Sabor",
            tapa = Tapa(
                id = 4,
                name = "Tosta de solomillo",
                description = "Pan tostado con solomillo, queso de cabra y cebolla caramelizada.",
                price = 5.50,
                rating = 4
            ),
            phone = "+34 968 24 09 32",
            address = "Avenida de la Libertad, 14, 30009 Murcia",
            web = "https://laesquinadelsabor.es"
        ),
        Bar(
            id = 5,
            name = "Mesón Los Olivos",
            tapa = Tapa(
                id = 5,
                name = "Patatas bravas",
                description = "Patatas doradas acompañadas de salsa brava casera y alioli.",
                price = 3.00,
                rating = 3
            ),
            phone = "+34 968 25 63 44",
            address = "Calle Sagasta, 21, 30001 Murcia",
            web = "https://mesonlosolivos.es"
        ),
        Bar(
            id = 6,
            name = "El Patio de la Abuela",
            tapa = Tapa(
                id = 6,
                name = "Caballito murciano",
                description = "Gamba rebozada en una fina capa crujiente.",
                price = 3.80,
                rating = 4
            ),
            phone = "+34 968 26 77 15",
            address = "Plaza San Juan, 3, 30003 Murcia",
            web = "https://elpatiodelaabuela.es"
        ),
        Bar(
            id = 7,
            name = "Bodega La Cepa",
            tapa = Tapa(
                id = 7,
                name = "Queso al horno",
                description = "Queso de cabra horneado con miel y nueces.",
                price = 4.50,
                rating = 5
            ),
            phone = "+34 968 27 88 26",
            address = "Calle Victorio, 9, 30003 Murcia",
            web = "https://bodegalacepa.es"
        ),
        Bar(
            id = 8,
            name = "El Fogón Mediterráneo",
            tapa = Tapa(
                id = 8,
                name = "Michirones",
                description = "Habas secas guisadas al estilo murciano con chorizo y panceta.",
                price = 4.00,
                rating = 4
            ),
            phone = "+34 968 28 34 58",
            address = "Paseo de Garay, 17, 30003 Murcia",
            web = "https://elfogonmediterraneo.es"
        )
    )
}
