import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

// 1️⃣ Login na aplicação
WebUI.callTestCase(findTestCase('saucedemo/Login positive'), [:], FailureHandling.STOP_ON_FAILURE)

// 2️⃣ Ordenar os itens do maior para o menor preço
WebUI.click(findTestObject('Object Repository/Saucedemo/Page_Swag Labs/span_Name (A to Z)Name (A to Z)Name (Z to A_3f2346'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Saucedemo/Page_Swag Labs/select_Name (A to Z)Name (Z to A)Price (low_f7e90a'), 
    'hilo', true)

// 3️⃣ Capturar os preços dos produtos na lista
List<WebElement> precos = WebUI.findWebElements(findTestObject('Object Repository/Saucedemo/Page_Swag Labs/Preco'), 10)

// 4️⃣ Ver os preços que estão na lista
println("Preços capturados da página: $precos*.getText()")

// 5️⃣ Converter os preços para números
List<Double> valores = precos.collect({ 
        it.getText().replaceAll('[^0-9.]', '').toDouble()
    })

// 6️⃣ Ver os valores armazenados nas variáveis
println("Valores convertidos para números: $valores")

// 7️⃣ Validar que o primeiro item tem o maior preço e o segundo tem o segundo maior
Double maiorValor = valores.max()

Double menorValor = valores.min()

// 8️⃣ Ver os maiores e menores valores
println("Maior valor: $maiorValor")

println("Menor valor: $menorValor")

// Verificar se o maior valor está no topo
boolean primeiroMaior = (valores[0]) == maiorValor

// Verificar se o segundo valor não é o menor
boolean segundoMenor = (valores[1]) != menorValor

// Realizar as verificações
if (primeiroMaior && segundoMenor) {
    WebUI.comment('Validação concluída: O primeiro item tem o maior preço e o segundo não tem o menor.')
} else {
    WebUI.comment('Falha na validação: A ordem dos preços não está correta.')
}

WebUI.closeBrowser()

