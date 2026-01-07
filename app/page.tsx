import { Badge } from "@/components/ui/badge"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Separator } from "@/components/ui/separator"

export default function Home() {
  return (
    <main className="min-h-screen bg-gradient-to-b from-slate-900 to-slate-800 text-white">
      {/* Header */}
      <header className="container mx-auto px-4 py-16 text-center">
        <Badge variant="outline" className="mb-4 border-emerald-500 text-emerald-400">
          Java Application
        </Badge>
        <h1 className="text-4xl md:text-6xl font-bold mb-4">Smart Parking Management System</h1>
        <p className="text-xl text-slate-300 max-w-2xl mx-auto">
          A Java-based parking lot management application demonstrating advanced Object-Oriented Programming principles
          with industry-standard architecture.
        </p>
        <div className="mt-8 flex gap-4 justify-center">
          <a
            href="https://github.com/timkanda/smart-parking-management-system"
            target="_blank"
            rel="noopener noreferrer"
            className="inline-flex items-center gap-2 bg-white text-slate-900 px-6 py-3 rounded-lg font-semibold hover:bg-slate-200 transition-colors"
          >
            <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
              <path
                fillRule="evenodd"
                d="M12 2C6.477 2 2 6.484 2 12.017c0 4.425 2.865 8.18 6.839 9.504.5.092.682-.217.682-.483 0-.237-.008-.868-.013-1.703-2.782.605-3.369-1.343-3.369-1.343-.454-1.158-1.11-1.466-1.11-1.466-.908-.62.069-.608.069-.608 1.003.07 1.531 1.032 1.531 1.032.892 1.53 2.341 1.088 2.91.832.092-.647.35-1.088.636-1.338-2.22-.253-4.555-1.113-4.555-4.951 0-1.093.39-1.988 1.029-2.688-.103-.253-.446-1.272.098-2.65 0 0 .84-.27 2.75 1.026A9.564 9.564 0 0112 6.844c.85.004 1.705.115 2.504.337 1.909-1.296 2.747-1.027 2.747-1.027.546 1.379.202 2.398.1 2.651.64.7 1.028 1.595 1.028 2.688 0 3.848-2.339 4.695-4.566 4.943.359.309.678.92.678 1.855 0 1.338-.012 2.419-.012 2.747 0 .268.18.58.688.482A10.019 10.019 0 0022 12.017C22 6.484 17.522 2 12 2z"
                clipRule="evenodd"
              />
            </svg>
            View on GitHub
          </a>
          <a
            href="https://www.linkedin.com/in/timothy-kanda-aaa190b4/"
            target="_blank"
            rel="noopener noreferrer"
            className="inline-flex items-center gap-2 bg-blue-600 text-white px-6 py-3 rounded-lg font-semibold hover:bg-blue-700 transition-colors"
          >
            <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
              <path d="M20.447 20.452h-3.554v-5.569c0-1.328-.027-3.037-1.852-3.037-1.853 0-2.136 1.445-2.136 2.939v5.667H9.351V9h3.414v1.561h.046c.477-.9 1.637-1.85 3.37-1.85 3.601 0 4.267 2.37 4.267 5.455v6.286zM5.337 7.433c-1.144 0-2.063-.926-2.063-2.065 0-1.138.92-2.063 2.063-2.063 1.14 0 2.064.925 2.064 2.063 0 1.139-.925 2.065-2.064 2.065zm1.782 13.019H3.555V9h3.564v11.452zM22.225 0H1.771C.792 0 0 .774 0 1.729v20.542C0 23.227.792 24 1.771 24h20.451C23.2 24 24 23.227 24 22.271V1.729C24 .774 23.2 0 22.222 0h.003z" />
            </svg>
            Connect on LinkedIn
          </a>
        </div>
      </header>

      <Separator className="bg-slate-700" />

      {/* Features Section */}
      <section className="container mx-auto px-4 py-16">
        <h2 className="text-3xl font-bold text-center mb-12">Core Functionality</h2>
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          {[
            { title: "Slot Management", desc: "Add and delete parking slots for Staff and Visitors" },
            { title: "Vehicle Parking", desc: "Park and remove vehicles with comprehensive validation" },
            { title: "Smart Search", desc: "Find cars by registration number or slot ID instantly" },
            { title: "Duration Tracking", desc: "Automatic parking time and duration calculation" },
            { title: "Fee Calculation", desc: "Multiple pricing strategies with Strategy pattern" },
            { title: "Data Persistence", desc: "Save and load parking data to JSON files" },
          ].map((feature, i) => (
            <Card key={i} className="bg-slate-800 border-slate-700">
              <CardHeader>
                <CardTitle className="text-emerald-400">{feature.title}</CardTitle>
              </CardHeader>
              <CardContent>
                <CardDescription className="text-slate-300">{feature.desc}</CardDescription>
              </CardContent>
            </Card>
          ))}
        </div>
      </section>

      <Separator className="bg-slate-700" />

      {/* Technical Highlights */}
      <section className="container mx-auto px-4 py-16">
        <h2 className="text-3xl font-bold text-center mb-12">Technical Highlights</h2>
        <div className="grid md:grid-cols-2 gap-8 max-w-4xl mx-auto">
          <Card className="bg-slate-800 border-slate-700">
            <CardHeader>
              <CardTitle className="text-white">OOP Principles</CardTitle>
            </CardHeader>
            <CardContent className="space-y-2">
              <div className="flex justify-between">
                <span className="text-slate-400">Interfaces</span>
                <span className="text-emerald-400">IParkable, ISearchable, IFeeCalculator</span>
              </div>
              <div className="flex justify-between">
                <span className="text-slate-400">Inheritance</span>
                <span className="text-emerald-400">StaffSlot, VisitorSlot</span>
              </div>
              <div className="flex justify-between">
                <span className="text-slate-400">Encapsulation</span>
                <span className="text-emerald-400">Private fields, public accessors</span>
              </div>
              <div className="flex justify-between">
                <span className="text-slate-400">Polymorphism</span>
                <span className="text-emerald-400">Strategy pattern implementation</span>
              </div>
            </CardContent>
          </Card>

          <Card className="bg-slate-800 border-slate-700">
            <CardHeader>
              <CardTitle className="text-white">Design Patterns</CardTitle>
            </CardHeader>
            <CardContent className="space-y-2">
              <div className="flex justify-between">
                <span className="text-slate-400">Singleton</span>
                <span className="text-emerald-400">CarPark instance management</span>
              </div>
              <div className="flex justify-between">
                <span className="text-slate-400">Factory</span>
                <span className="text-emerald-400">ParkingSlotFactory</span>
              </div>
              <div className="flex justify-between">
                <span className="text-slate-400">Strategy</span>
                <span className="text-emerald-400">Fee calculation algorithms</span>
              </div>
            </CardContent>
          </Card>

          <Card className="bg-slate-800 border-slate-700">
            <CardHeader>
              <CardTitle className="text-white">Quality Assurance</CardTitle>
            </CardHeader>
            <CardContent className="space-y-2">
              <div className="flex justify-between">
                <span className="text-slate-400">Testing</span>
                <span className="text-emerald-400">JUnit 5 (40+ test cases)</span>
              </div>
              <div className="flex justify-between">
                <span className="text-slate-400">Exceptions</span>
                <span className="text-emerald-400">8 custom exception types</span>
              </div>
              <div className="flex justify-between">
                <span className="text-slate-400">Validation</span>
                <span className="text-emerald-400">Comprehensive input checks</span>
              </div>
            </CardContent>
          </Card>

          <Card className="bg-slate-800 border-slate-700">
            <CardHeader>
              <CardTitle className="text-white">Technologies</CardTitle>
            </CardHeader>
            <CardContent className="space-y-2">
              <div className="flex justify-between">
                <span className="text-slate-400">Language</span>
                <span className="text-emerald-400">Java 11+</span>
              </div>
              <div className="flex justify-between">
                <span className="text-slate-400">Build Tool</span>
                <span className="text-emerald-400">Maven</span>
              </div>
              <div className="flex justify-between">
                <span className="text-slate-400">Persistence</span>
                <span className="text-emerald-400">Gson (JSON)</span>
              </div>
              <div className="flex justify-between">
                <span className="text-slate-400">Data Structure</span>
                <span className="text-emerald-400">HashMap (O(1) lookup)</span>
              </div>
            </CardContent>
          </Card>
        </div>
      </section>

      <Separator className="bg-slate-700" />

      {/* Project Structure */}
      <section className="container mx-auto px-4 py-16">
        <h2 className="text-3xl font-bold text-center mb-12">Project Structure</h2>
        <Card className="bg-slate-800 border-slate-700 max-w-2xl mx-auto">
          <CardContent className="pt-6">
            <pre className="text-sm text-slate-300 overflow-x-auto">
              {`src/main/java/com/parkingsystem/
├── interfaces/      # Contract definitions
├── models/          # Car, ParkingSlot, StaffSlot, VisitorSlot
├── services/        # CarPark (Singleton), FeeCalculator
├── factory/         # ParkingSlotFactory
├── exceptions/      # Custom exception hierarchy
├── persistence/     # JSON data storage
└── utils/           # Validation utilities`}
            </pre>
          </CardContent>
        </Card>
      </section>

      {/* Footer */}
      <footer className="container mx-auto px-4 py-8 text-center text-slate-400">
        <p>
          Built by{" "}
          <a
            href="https://github.com/timkanda"
            target="_blank"
            rel="noopener noreferrer"
            className="text-emerald-400 hover:underline"
          >
            Timothy Kanda
          </a>
        </p>
        <p className="mt-2 text-sm">MIT License</p>
      </footer>
    </main>
  )
}
