

// anzeige.dto.ts
export class AnzeigeDto {
  id: number; // Annahme: Eindeutige Identifikationsnummer der Anzeige
  product: string;
  description: string;
  price: number;

  constructor(id: number, product: string, description: string, price: number) {
    this.id = id;
    this.product = product;
    this.description = description;
    this.price = price;
  }
}
