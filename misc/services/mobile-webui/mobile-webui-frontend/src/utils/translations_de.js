const translations = {
  appName: 'metasfresh mobile',
  error: {
    PleaseTryAgain: 'Oops, das sollte nicht passieren',
    network: {
      noResponse: 'Verbindung Fehler',
    },
  },
  general: {
    Product: 'Produkt',
    Locator: 'Lagerort',
    Barcode: 'Barcode',
    QtyToPick: 'Pick Menge',
    QtyPicked: 'Menge gepickt',
    QtyMoved: 'Menge bewegt',
    QtyToMove: 'Bewegungsmenge',
    QtyRejected: 'Menge verworfen',
    DropToLocator: 'Ziel Lagerort',
    cancelText: 'Abbrechen',
  },
  login: {
    submitButton: 'Login',
  },
  mobileui: {
    manufacturing: {
      appName: 'Produktion',
    },
    picking: {
      appName: 'Kommissionierung',
    },
    distribution: {
      appName: 'Bereitstellung',
    },
  },
  activities: {
    scanBarcode: {
      defaultCaption: 'Scan',
      invalidScannedBarcode: 'Code ist ungültig',
    },
    abortText: 'Verlassen',
    picking: {
      PickingLine: 'Pick Zeile',
      scanQRCode: 'Scan QR',
      notEligibleHUBarcode: 'HU Code passt nicht',
      invalidQtyPicked: 'Falsche Menge gepickt',
      confirmDone: 'OK',
      rejectedPrompt: 'Es gibt %(qtyRejected)s %(uom)s ungepickte Mengen. Warum?',
      unPickBtn: 'Rückgängig',
      target: 'Soll',
      picked: 'Ist',
    },
    distribution: {
      DistributionLine: 'Bereitstellung Zeile',
      target: 'Soll',
      picked: 'Ist',
      scanHU: 'Scan HU',
      scanLocator: 'Scan Ziel Lagerort',
      invalidLocatorBarcode: 'Lagerort Barcode ungültig',
      invalidQtyToMove: 'Bewegungsmenge ungültig',
    },
    confirmButton: {
      default: {
        caption: 'Bestätigen',
        promptQuestion: 'Bist du sicher?',
        yes: 'Ja',
        no: 'Nein',
      },
      abort: 'Rückgängig',
      notFound: 'Nicht gefunden',
    },
    mfg: {
      ProductName: 'Produkt',
      target: 'Soll',
      picked: 'Ist',
      issues: {
        target: 'Zuf. Soll',
        picked: 'Ist',
        qtyToIssue: 'Menge Soll',
        qtyIssued: 'Menge Ist',
        qtyRejected: 'Menge verworfen',
      },
      receipts: {
        qtyToReceiveTarget: 'Sollmenge',
        qtyReceived: 'Produziert',
        qtyToReceive: 'noch offen',
        btnReceiveTarget: 'Gebinde',
        btnReceiveProducts: 'Produzieren',
        existingLU: 'Scan',
        newHU: 'Neues Gebinde',
        target: 'Empf. Soll',
        picked: 'Ist',
      },
    },
  },
};

export default translations;
