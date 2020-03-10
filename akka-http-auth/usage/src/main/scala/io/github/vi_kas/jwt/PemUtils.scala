package io.github.vi_kas.jwt

import java.io.{File, FileReader}
import java.security.interfaces.{RSAPrivateKey, RSAPublicKey}
import java.security.spec.{InvalidKeySpecException, PKCS8EncodedKeySpec, X509EncodedKeySpec}
import java.security.{KeyFactory, NoSuchAlgorithmException, PrivateKey, PublicKey}

import org.bouncycastle.util.io.pem.PemReader

import scala.util.Try

object PemUtils {

  private def parsePEMFile(pemFile: File): Try[Array[Byte]] =
    Try {
      val reader = new PemReader(new FileReader(pemFile))
      val pem = reader.readPemObject()
      reader.close()

      pem.getContent
    }

  def getRSAPublicKey(pemFile: String, algorithm: String = "RSA") = {

    parsePEMFile(new File(pemFile))
      .map(content => {

        var publicKey: PublicKey = null
        try {
          val kf = KeyFactory.getInstance(algorithm)
          val keySpec = new X509EncodedKeySpec(content)
          publicKey = kf.generatePublic(keySpec)
        } catch {
          case e: NoSuchAlgorithmException =>
            println("Could not reconstruct the public key, the given algorithm could not be found.")
          case e: InvalidKeySpecException =>
            println("Could not reconstruct the public key")
        }

        publicKey.asInstanceOf[RSAPublicKey]
      })
  }

  def getRSAPrivateKey(pemFile: String, algorithm: String = "RSA") = {

    parsePEMFile(new File(pemFile))
      .map(content => {
        var privateKey: PrivateKey = null

        try {
          val kf = KeyFactory.getInstance(algorithm)
          val keySpec = new PKCS8EncodedKeySpec(content)

          privateKey = kf.generatePrivate(keySpec)
        } catch {
          case e: NoSuchAlgorithmException =>
            println("Could not reconstruct the private key, the given algorithm could not be found.")
          case e: InvalidKeySpecException =>
            println("Could not reconstruct the private key")
        }

        privateKey.asInstanceOf[RSAPrivateKey]
      })
  }

}