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
            )
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
            )
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
            )
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
            )
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
            )
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
            )
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
            )
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
            )
        )
    )
}